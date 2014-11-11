import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import opennlp.tools.util.InvalidFormatException;

import org.junit.Test;

import com.ideas.SentimentAnalysis.core.AspectSentimentPair;
import com.ideas.SentimentAnalysis.core.ConsoleDisplay;
import com.ideas.SentimentAnalysis.core.OpenNLPIntegration;
import com.ideas.SentimentAnalysis.core.Sentence;
import com.ideas.SentimentAnalysis.core.SentiWordNet;
import com.ideas.SentimentAnalysis.core.SentimentAnalyzer;


public class SocialInsightsModuleAcceptanceTest implements ConsoleDisplay {

    private SentimentAnalyzer sentiAnalyzer;
    private OpenNLPIntegration opennlp ;

    private String actualDisplayMsg;
    private int propertyid;
    private StringBuilder collatedreviewdoc;
    private String[] sentences;
    private SentiWordNet sentiWordNet;
    private List<Sentence> sentenceList;
    HashMap<Integer,List<AspectSentimentPair>> aspectSentimentMap;

    @Test
    public void generateSentimentNumbersForFixedAspectstest() throws InvalidFormatException, IOException, InterruptedException {

        extractReviewsFromDatabase(propertyid).display("All the reviews extracted ...");
        //        Thread.sleep(10000);
        detectSentencesFromTheCollatedReviews().display("Done with Sentence detection and seperation ...");
        //        Thread.sleep(10000);
        loadWordNetandSentiWordNetDictionay().display("Wordnet and SentiWordNet Dictionaries loaded ...");
        //        Thread.sleep(10000);
        partOfSpeechTaggingAndAspectMiningOfReviews().display("All sentences POS tagged and Aspects Mined ...");
        //        Thread.sleep(10000);
        extractAspectSentimentPairsFromEachStatement().display("Extracting Aspects and associated Sentiments ...");
        //        Thread.sleep(10000);
        categorizationAndAssociatingValueToSentiments().display("Categorizing the Aspects into categories and associating values to sentiments ...");


    }
    // STEP 1
    private SocialInsightsModuleAcceptanceTest extractReviewsFromDatabase(int propertyid) {
        opennlp = new OpenNLPIntegration(this);
        sentiAnalyzer = new SentimentAnalyzer(propertyid , this , opennlp);
        collatedreviewdoc = sentiAnalyzer.getReviewsCollated();
        actualDisplayMsg ="All the reviews extracted ...";
        return this;
    }

    // STEP 2
    private SocialInsightsModuleAcceptanceTest detectSentencesFromTheCollatedReviews() throws InvalidFormatException, IOException {
        sentences = opennlp.SentenceDetect(collatedreviewdoc.toString());
        return this;
    }

    // STEP 3
    private SocialInsightsModuleAcceptanceTest loadWordNetandSentiWordNetDictionay() throws IOException {
        sentiWordNet = new SentiWordNet(this);
        return this;
    }

    // STEP 4
    private SocialInsightsModuleAcceptanceTest partOfSpeechTaggingAndAspectMiningOfReviews() throws IOException {
        sentenceList = sentiAnalyzer.processSentences(sentences);
        return this;
    }


    // STEP 5
    private SocialInsightsModuleAcceptanceTest extractAspectSentimentPairsFromEachStatement() {
        aspectSentimentMap = sentiAnalyzer.extractAspectSentimentPairsFromEachStatement(sentenceList);
        return this;
    }

    // STEP 6
    private SocialInsightsModuleAcceptanceTest categorizationAndAssociatingValueToSentiments() {
        sentiAnalyzer.categorizationAndAssociatingValueToSentiments(aspectSentimentMap , sentenceList ,sentiWordNet);
        return this;
    }

    private void display(String msg) {
        assertEquals(msg, actualDisplayMsg);
    }
    @Override
    public void DisplayOnConsole(String msg) {
        actualDisplayMsg = msg;
        System.out.println(" -- : "+ msg);

    }

}
