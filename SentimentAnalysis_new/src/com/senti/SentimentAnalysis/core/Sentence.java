package com.ideas.SentimentAnalysis.core;

public class Sentence {
	
	private  static final int MAX_NOUN_ADJ_LIMIT_IN_SENTENCE = 300;
	
	private int sentenceId;
	private String sentence;
	private String[] nouns = null;
	private String[] adjectives = null;
	private Integer[] nounDistance = null;
	private Integer[] adjectivesDistance = null;
	
	private int currentNounCount = 0 ;
	private int currentAdjectiveCount = 0 ;
	
	public Sentence()
	{
		nouns               = new String[MAX_NOUN_ADJ_LIMIT_IN_SENTENCE] ;
		adjectives          = new String[MAX_NOUN_ADJ_LIMIT_IN_SENTENCE] ;
		nounDistance        = new Integer[MAX_NOUN_ADJ_LIMIT_IN_SENTENCE];
		adjectivesDistance  = new Integer[MAX_NOUN_ADJ_LIMIT_IN_SENTENCE];
	}

	public int getSentenceId() {
		return sentenceId;
	}

	public void setSentenceId(int sentenceId) {
		this.sentenceId = sentenceId;
	}

	public Integer[] getNounDistance() {
		return nounDistance;
	}

	public void setNounDistance(int nounDistance) {
		this.nounDistance[currentNounCount] = nounDistance;
		currentNounCount++;
	}

	public Integer[] getAdjectivesDistance() {
		return adjectivesDistance;
	}

	public void setAdjectivesDistance(int adjectivesDistance) {
		this.adjectivesDistance[currentAdjectiveCount] = adjectivesDistance;
		currentAdjectiveCount++;
	}
	
	public void setNoun(String noun)
	{
		nouns[currentNounCount] = noun;		
	}
	
	public void setAdjective(String adjective)
	{
		adjectives[currentAdjectiveCount] = adjective;
	}

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}
	
	public String[] getNouns() {
		return nouns;
	}

	public String[] getAdjectives() {
		return adjectives;
	}

	
	

}
