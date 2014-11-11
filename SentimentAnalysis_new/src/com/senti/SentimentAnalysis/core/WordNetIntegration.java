package com.ideas.SentimentAnalysis.core;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import edu.smu.tspell.wordnet.NounSynset;


import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.SynsetType;
import edu.smu.tspell.wordnet.WordNetDatabase;

public class WordNetIntegration {

	static Set<String> wordstosearch = new HashSet<String>();
	static int wordsearchcount = 0;
	static String myworddic = "Good Bad ";
	
	private static WordNetDatabase wordnetdb;
	
	WordNetIntegration()
	{
		System.setProperty("wordnet.database.dir","C:\\Program Files (x86)\\WordNet\\2.1\\dict\\");
		wordnetdb = WordNetDatabase.getFileInstance();
	}


	public void printCollection(Collection collection) {

		for (Object obj : collection) {
			System.out.println(obj);
		}
	}
	
	
	private StringBuilder getSynonyms(String inputWordString)
	{
		StringBuilder synonyms = new StringBuilder();		
		String[] wordFormArray = inputWordString.split(" ");
		
		//WordNetDatabase wordnetdb = WordNetDatabase.getFileInstance();

		for (int wordcnt = 0; wordcnt < wordFormArray.length; wordcnt++) {
			StringBuilder wordForm = new StringBuilder(wordFormArray[wordcnt]);
			Synset[] synsets1 = wordnetdb.getSynsets(wordForm.toString());
	
			if (synsets1.length > 0) {
				for (int i = 0; i < synsets1.length; i++) {

					String[] wordForms = synsets1[i].getWordForms();
					for (int j = 0; j < wordForms.length; j++) {					
						synonyms.append(wordForms[j]+ " ");
					}
					
				}
			} else {
				System.err.println("No synsets exist that contain "	+ "the word form '" + wordForm + "'");
			}
		}

		this.printCollection(wordstosearch);
		return synonyms;
	}
	
//	public static void main(String args[]) {
//
//		System.setProperty("wordnet.database.dir","C:\\Program Files (x86)\\WordNet\\2.1\\dict\\");
//
//		NounSynset nounSynset;
//		NounSynset[] hyponyms;
//
//		WordNetDatabase database = WordNetDatabase.getFileInstance();
//		
//		
//		Synset[] synsets = database.getSynsets("fly", SynsetType.NOUN);
//		for (int i = 0; i < synsets.length; i++) {
//			nounSynset = (NounSynset) (synsets[i]);
//			hyponyms = nounSynset.getHyponyms();
//			System.err.println(nounSynset.getWordForms()[0] + ": "
//					+ nounSynset.getDefinition() + ") has " + hyponyms.length
//					+ " hyponyms");
//		}
//
//		// if (args.length > 0)
//		// {
//		// Concatenate the command-line arguments
//		StringBuffer buffer = new StringBuffer();
//		for (int i = 0; i < args.length; i++) {
//			buffer.append((i > 0 ? " " : "") + args[i]);
//		}
//
//
//		// }
//		// else
//		// {
//		// System.err.println("You must specify " +
//		// "a word form for which to retrieve synsets.");
//		// }
//		
//
//	}

	
}
