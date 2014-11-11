package com.ideas.SentimentAnalysis.service;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;

import opennlp.tools.util.HashList;

import com.ideas.SentimentAnalysis.core.OpenNLPIntegration;
import com.ideas.SentimentAnalysis.core.SentiWordNet;
import com.ideas.SentimentAnalysis.core.SentimentAnalyzer;

public class SocialInsightsService {
	
ArrayList<String> StopNounWords = new ArrayList<String>();


	
	public static void main(String[] args) throws IOException
	{
		SentimentAnalyzer sa = new SentimentAnalyzer(1,null,null);
		StringBuilder collatedReviews = sa.getReviewsCollated();
		
		OpenNLPIntegration nlp = new OpenNLPIntegration(null);
		String postaggedreviews = nlp.POSTag(collatedReviews.toString(), null);
		
		String[] splits = postaggedreviews.split(" ");
		
//		HashMap<String,Set<String>> wordtagbag = new HashMap<String,Set<String>>();
//		
//		for(String s: splits)
//		{
//			String[] KeyValue = s.split("_");
//			
//			Set<String> valueSet = wordtagbag.get(KeyValue[1]);
//			
//			if(valueSet == null)
//			{ 
//				valueSet = new HashSet<String>();
//				valueSet.add(KeyValue[0]);
//			}
//			else
//			{
//				valueSet.add(KeyValue[0]);
//			}
//			
//			wordtagbag.put(KeyValue[1],valueSet);
//			
//		}
//			
//		Set<String> KeySet = wordtagbag.keySet();
//	
//        for(String key: KeySet){
//            System.out.println(key);
//        }
//	
//        Set<String> valueSetNouns = wordtagbag.get("NNS");
//        System.out.println(valueSetNouns);
//        
//	
//        
//        Set<String> valueSetAdj = wordtagbag.get("JJ");
//        System.out.println(valueSetAdj);
        
        
        String[] sentences = nlp.SentenceDetect(collatedReviews.toString());
        SentiWordNet senti = new SentiWordNet(null);
        
        // ----------------------------------------------------------------------
        int maxleg = 55;
        String[] NounPhrases = new String [50000];
        HashMap<String,Integer> SubjectCount =  new HashMap<String,Integer>();
        Stemmer stem = new Stemmer();
        java.util.List afterstemming = new ArrayList<String>();
        
        Set NounSet = new HashSet<String>();
        String[] Nouns = new String[sentences.length * maxleg];
        String[] Verbs = new String[sentences.length * maxleg];
        String[] Adjectives = new String[sentences.length * maxleg];
        double[] score = new double[sentences.length * maxleg];
        int scount = 1;
        int count = 1;
        StringBuilder nounpharases = new StringBuilder();
        for(String review: sentences)
        {
        	String postaggedreview = nlp.POSTag(review, null);
        	System.out.println(postaggedreview);
        	String[] splitwords = postaggedreview.split(" ");
        	
        	int n = 0,v = 0,a = 0;
        	for(String s: splitwords)
    		{
        		
        		String[] wordTag = s.split("_");
        		
        		if(wordTag.length > 2)
        			break;
        		
        		if(wordTag[1].contains(".") || wordTag[0].contains("."))
        			break;
        		
        		if(wordTag[1].equals("NNS") || wordTag[1].equals("NN"))
        		{
        			
        			      			
        			afterstemming = stem.getStems(wordTag[0]);
        			
        			Nouns[scount]= wordTag[0];
        			NounPhrases[count] = wordTag[0];
        			
        			for (Object element : afterstemming) {
        				
        				if(SubjectCount.containsKey(element.toString()))
            			{
            				int currentcount = SubjectCount.get(element);
            				SubjectCount.put((String) element, currentcount + 1);
            			}
            			else
            				SubjectCount.put((String) element, 1);
        			}
//        			if(SubjectCount.containsKey(wordTag[0]))
//        			{
//        				int currentcount = SubjectCount.get(wordTag[0]);
//        				SubjectCount.put(wordTag[0], currentcount + 1);
//        			}
//        			else
//        				SubjectCount.put(wordTag[0], 1);
        				
        			
        			NounSet.add(wordTag[0]);
        			nounpharases.append(wordTag[0]+",");        			
        			n++;
        			count++;
        			
        		}
        		else if (wordTag[1].equals("VB") || wordTag[1].equals("VBD") || wordTag[1].equals("VBG") || wordTag[1].equals("VBN") || wordTag[1].equals("VBP"))
        		{
        			Verbs[scount] = wordTag[0];
        			v++;
        		}
        		else if (wordTag[1].equals("JJ"))
        		{
        			Adjectives[scount]= wordTag[0];
        			score[scount] = senti.extract(wordTag[0], "a");
        			a++;
        		}
        		
    		}
        	scount++;
        }
        
        
        System.out.println(" Noun        Verb         Adjective");
       
        for(int i = 1;i < scount ;i++)
        {
        	
        		System.out.println(i + "     "+ Nouns[i] +"     "+ Verbs[i] +"     " + Adjectives[i] + "     "+score[i] +"  "+sentences[i-1]);
        }
        nlp.Parse(sentences[0]);
        nlp.Parse(sentences[1]);
        nlp.Parse(sentences[2]);
        nlp.Parse(sentences[3]);
        nlp.Parse(sentences[4]);
        
        System.out.println();
   
        for(int i = 1 ;i < count ;i++)
        {
    		System.out.println(i + "     "+ NounPhrases[i] );
   
        }
        System.out.println("Noun Count"+count);
        System.out.println("Unique Nouns:"+NounSet.size());
        
        for (Entry<String, Integer> entry : SubjectCount.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
       
        	
	}

}


// Sentiment Categories
// OVerall
// Rooms
// Hotel / place / property
// Food / Beverage / drinks /
// Others