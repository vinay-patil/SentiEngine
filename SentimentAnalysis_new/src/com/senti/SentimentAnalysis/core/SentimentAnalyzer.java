package com.ideas.SentimentAnalysis.core;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.hospitality.sentimentanalysis.database.DatabaseQueryExecuter;
import com.ideas.SentimentAnalysis.service.Stemmer;

public class SentimentAnalyzer {


    private static final int MAX_PERMISSIBLE_ASPECT_SENTIMENT_DISTANCE = 3;
    private int propertyid;
    private Logger logger;
    private ConsoleDisplay consoleDisplay;
    private OpenNLPIntegration openNLP;
    private Stemmer stemmer;
    private POSModel model;

    public SentimentAnalyzer(int propertyid, ConsoleDisplay consoleDisplay , OpenNLPIntegration openNLP) {

        this.propertyid = propertyid;
        logger = Logger.getLogger(SentimentAnalyzer.class.getName());
        this.consoleDisplay = consoleDisplay;
        this.openNLP = openNLP;
        stemmer = new Stemmer();
        model = new POSModelLoader().load(new File("C:\\MySystem\\V5i\\Eclipse\\FunctionSpace\\JavaWordnet\\model\\en-pos-maxent.bin"));

    }


    public StringBuilder getReviewsCollated()
    {

        logger.debug("Start collecting reviews");
        return com.hospitality.sentimentanalysis.database.DatabaseQueryExecuter.template().query("  SELECT ReviewComments FROM REVIEWMASTER " ,
                new ResultSetExtractor(){

            StringBuilder collatedreviewdoc = null;
            @Override
            public StringBuilder extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next())
                {
                    if(collatedreviewdoc == null) {
                        collatedreviewdoc = new StringBuilder();
                    }

                    collatedreviewdoc.append(rs.getString(1));
                }
                return collatedreviewdoc;
            }

        });
    }

    public List<Sentence> processSentences(String[] sentences) throws IOException
    {

        int scount = 0;
        Sentence currentSentence = null;
        List<String> afterstemming = null;
        List<Sentence> sentenceList = new ArrayList<Sentence>();


        for(String review: sentences)
        {

            currentSentence = new Sentence();
            currentSentence.setSentenceId(scount);
            currentSentence.setSentence(review);

            String postaggedreview = openNLP.POSTag(review , model);

            if(scount > 20 && scount <= 23) {
                System.out.println("POS TAGGED Sample ----- "+postaggedreview);
            }

            String[] splitwords = postaggedreview.split(" ");

            int n = 0,v = 0,a = 0;
            int wcount = 0;
            for(String s: splitwords)
            {

                String[] wordTag = s.split("_");

                if(wordTag.length > 2 || wordTag[1].contains(".") || wordTag[0].contains(".")) {
                    break;
                }

                if(wordTag[1].equals("NNS") || wordTag[1].equals("NN"))
                {
                    currentSentence.setNoun(wordTag[0]);
                    currentSentence.setNounDistance(wcount);
                }
                else if (wordTag[1].equals("JJ"))
                {
                    currentSentence.setAdjective(wordTag[0]);
                    currentSentence.setAdjectivesDistance(wcount);
                }
                wcount++; // increment word count
            }

            sentenceList.add(currentSentence);
            scount++; // increment sentence count
        }


        consoleDisplay.DisplayOnConsole("All sentences POS tagged and Aspects Mined ...");
        return sentenceList;
    }

    public HashMap<Integer,List<AspectSentimentPair>> extractAspectSentimentPairsFromEachStatement(List<Sentence> sentenceList)
    {
        HashMap<Integer,List<AspectSentimentPair>> aspectSentiment = new HashMap<Integer,List<AspectSentimentPair>>();

        AspectSentimentPair aspectSentimentPair = null;
        Integer key = null;
        List<AspectSentimentPair> aspectSentimentList = null;
        String[] nouns = null;
        String[] adjectives = null;
        Integer[] nounDistance = null;
        Integer[] adjectivesDistance = null;


        for (Sentence sentence : sentenceList) {

            aspectSentimentList = new ArrayList<AspectSentimentPair>();
            key = sentence.getSentenceId();

            nouns = sentence.getNouns();
            adjectives = sentence.getAdjectives();
            nounDistance = sentence.getNounDistance();
            adjectivesDistance = sentence.getAdjectivesDistance();

            if(nouns[0] != null && adjectives[0] != null && nounDistance[0] != null && adjectivesDistance[0] != null)
            {
                int nounloopcount = 0;
                while(nouns[nounloopcount] != null)
                {
                    int currentnoundistance =nounDistance[nounloopcount];
                    int adjectiveloopcount = 0;

                    while(adjectives[adjectiveloopcount] != null)
                    {
                        int currentadjectivedistance = adjectivesDistance[adjectiveloopcount];
                        if( Math.abs((double)(currentnoundistance - currentadjectivedistance)) <= MAX_PERMISSIBLE_ASPECT_SENTIMENT_DISTANCE)
                        {
                            aspectSentimentPair = new AspectSentimentPair();
                            aspectSentimentPair.setAspect(nouns[nounloopcount]);
                            aspectSentimentPair.setSentiment(adjectives[adjectiveloopcount]);
                            aspectSentimentList.add(aspectSentimentPair);

                        }
                        adjectiveloopcount++;
                    }
                    nounloopcount++;

                }
            }

            if(aspectSentimentPair != null) {
                aspectSentiment.put(key, aspectSentimentList);
            }


        }
        consoleDisplay.DisplayOnConsole("Extracting Aspects and associated Sentiments ...");
        return 	aspectSentiment;
    }

    public void categorizationAndAssociatingValueToSentiments(HashMap<Integer,List<AspectSentimentPair>> aspectSentimentMap , List<Sentence> sentenceList, SentiWordNet sentiWordNet)
    {
        double negative = 0, positive = 0 , neutral = 0;
        double negativeR = 0, positiveR = 0 , neutralR = 0;
        double negativeF = 0, positiveF = 0 , neutralF = 0;
        double negativeS = 0, positiveS = 0 , neutralS = 0;
        double negativeH = 0, positiveH = 0 , neutralH = 0;
        double negativeL = 0, positiveL = 0 , neutralL = 0;

        for(int sentencecount = 0 ; sentencecount < sentenceList.size() ; sentencecount++)
        {
            List<AspectSentimentPair> aspectSentimentList = aspectSentimentMap.get(sentencecount);

            if(aspectSentimentList != null && aspectSentimentList.size() != 0)
            {

                for (AspectSentimentPair aspectSentimentPair : aspectSentimentList)
                {
                    String aspect = aspectSentimentPair.getAspect();
                    String sentiment = aspectSentimentPair.getSentiment();
                    double sentimentvalue = sentiWordNet.extract(sentiment, "a");

                    if(sentencecount > 22 && sentencecount < 26 ) {
                        System.out.println("                 Id "+sentencecount+"| Aspect --"+ aspect + "| Sentiment --" +  sentiment + "| Value :: " +  sentimentvalue);
                    }

                    if(sentimentvalue < 0) {
                        negative++;
                    } else if(sentimentvalue == 0) {
                        neutral++;
                    } else {
                        positive++;
                    }

                    if(aspect.contains("room"))
                    {
                        if(sentimentvalue < 0) {
                            negativeR++;
                        } else if(sentimentvalue == 0) {
                            neutralR++;
                        } else {
                            positiveR++;
                        }
                    }
                    else if(aspect.contains("food") || aspect.contains("breakfast") || aspect.contains("restaurant"))
                    {
                        if(sentimentvalue < 0) {
                            negativeF++;
                        } else if(sentimentvalue == 0) {
                            neutralF++;
                        } else {
                            positiveF++;
                        }
                    }
                    else if(aspect.contains("staff") || aspect.contains("service") )
                    {
                        if(sentimentvalue < 0) {
                            negativeS++;
                        } else if(sentimentvalue == 0) {
                            neutralS++;
                        } else {
                            positiveS++;
                        }
                    }
                    else if(aspect.contains("hotel") || aspect.contains("locat") || aspect.contains("place") )
                    {
                        if(sentimentvalue < 0) {
                            negativeH++;
                        } else if(sentimentvalue == 0) {
                            neutralH++;
                        } else {
                            positiveH++;
                        }
                    }

                }
            }
        }
        //		System.out.println("Final Sentiments :  Negative "+ negative + " Positive :"+ positive +" Neutral " + neutral);
        //		double negativepercent = ((double)negative/(double)(negative+positive+neutral));
        //		double positivepercent = (positive/(negative+positive+neutral));
        //		double neutralpercent  = (neutral/(negative+positive+neutral));


        System.out.println(" Overall % negative   :" +  negative/(negative+positive+neutral) +     " | Overall % positive   :"+positive/(negative+positive+neutral));
        System.out.println(" Rooms % negative     :" +  negativeR/(negativeR+positiveR+neutralR) + " | Rooms % positive     :"+positiveR/(negativeR+positiveR+neutralR));
        System.out.println(" Food % negative      :" +  negativeF/(negativeF+positiveF+neutralF) + " | Food % positive      :"+positiveF/(negativeF+positiveF+neutralF));
        System.out.println(" Staff % negative     :" +  negativeS/(negativeS+positiveS+neutralS) + " | Staff % positive     :"+positiveS/(negativeS+positiveS+neutralS));
        System.out.println(" Hotel % negative     :" +  negativeH/(negativeH+positiveH+neutralH) + " | Hotel % positive     :"+positiveH/(negativeH+positiveH+neutralH));

        // ---------------------------------------------------------------------------------

        DatabaseQueryExecuter.execute("TRUNCATE TABLE socialinsightsinference");
        StringBuilder collatedreviewdoc = null;

        StringBuilder sb = new StringBuilder();
        sb.append(  "  INSERT INTO socialinsightsinference " +
                "   VALUES(1,'OVERALL', " + negative/(negative+positive+neutral) + "," +positive/(negative+positive+neutral)+ "," +neutral/(negative+positive+neutral)  + " ); "
                );


        DatabaseQueryExecuter.execute(sb.toString());
        sb.setLength(0);

        sb.append( "  INSERT INTO socialinsightsinference " +
                "   VALUES(1,'ROOMS', " + negativeR/(negativeR+positiveR+neutralR) + ","+positiveR/(negativeR+positiveR+neutralR)+","+neutralR/(negativeR+positiveR+neutralR)+" ); "
                );
        DatabaseQueryExecuter.execute(sb.toString());
        sb.setLength(0);

        sb.append( "  INSERT INTO socialinsightsinference " +
                "   VALUES(1,'FOOD & BEVERAGE', " + negativeF/(negativeF+positiveF+neutralF) + ","+positiveF/(negativeF+positiveF+neutralF)+","+neutralF/(negativeF+positiveF+neutralF)+" ); "
                );
        DatabaseQueryExecuter.execute(sb.toString());
        sb.setLength(0);

        sb.append( "  INSERT INTO socialinsightsinference " +
                "   VALUES(1,'GENERAL STAFF', " + negativeS/(negativeS+positiveS+neutralS) + ","+positiveS/(negativeS+positiveS+neutralS)+","+neutralS/(negativeS+positiveS+neutralS)+" ); "
                );
        DatabaseQueryExecuter.execute(sb.toString());
        sb.setLength(0);

        sb.append( "  INSERT INTO socialinsightsinference " +
                "   VALUES(1,'GENERAL HOTEL', " + negativeH/(negativeH+positiveH+neutralH) + ","+positiveH/(negativeH+positiveH+neutralH)+","+neutralH/(negativeH+positiveH+neutralH)+" ); "
                );
        DatabaseQueryExecuter.execute(sb.toString());
        sb.setLength(0);

        consoleDisplay.DisplayOnConsole("Categorizing the Aspects into categories and associating values to sentiments ...");
    }
}

