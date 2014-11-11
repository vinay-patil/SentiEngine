package com.ideas.SentimentAnalysis.core;

import org.apache.commons.lang.StringUtils;
import org.jutil.java.collections.Stack;

public class ParseTrees {
	
	
	public static void main(String args[])
	{	
		String sentence = "(TOP (S (NP (DT The) (NNS rooms)) (VP (VBP are) (S (NP (JJ good,)) (VP (VBD stayed) (PP (IN in) (NP (NNP Superior))) (VP (VBD room,) (S (S (NP (PRP$ our) (NN room)) (VP (VBD was) (NP (NP (NN kind)) (PP (IN of) (NP (NN good,)))))) (CC but) (S (S (NP (DT the) (NN view)) (VP (VBD was) (VP (ADVP (RB poor,)) (VBD got) (NP (NP (DT the) (NN backside) (NN room)) (PP (IN with) (NP (NP (DT no) (NN view)) (PP (IN except) (NP (NP (DT the) (JJ boundary) (NN walls,)) (SBAR (S (NP (PRP they)) (VP (VBP have) (ADVP (RB recently)) (VP (VBN renovated) (NP (NP (DT some)) (PP (IN of) (NP (PRP$ their) (JJ superior) (NN rooms,)))))))))))))))) (CC but) (S (NP (PRP we)) (VP (MD couldn't) (VP (VB get) (NP (DT those))))))))))) (. rooms.)))";
		new ParseTrees().parseTreeString(sentence);
		
		int indexNP = sentence.indexOf("(NP");
        
        System.out.println(" IndexNP "+ indexNP);
        System.out.println("  "+sentence.substring(indexNP, sentence.length())); // this is correct :)
        System.out.println("  "+sentence.substring((indexNP+1), sentence.length()));
        System.out.println("  "+sentence.substring(indexNP, sentence.length()-1));
        ExtractNounSubtree(sentence);
        ExtractVerbSubtree(sentence);
	}
	
  String[] Sentiments = null;
  String[] Aspects = null;
  static String[] NounPhrases = null;
  static String[] VerbPhrases = null;
  
  public static int countnounphrases = 0;
  public static int countverbphrases = 0;
  
  public final int maxCount = 50000;
  public static final int currentCount = 0;
  
  public  ParseTrees()
  {
	Sentiments = new String[maxCount];  
	Aspects = new String[maxCount];
	
  }
	
	
	public static void parseTreeString(String sentence)
	{
		// Create a new, empty stack
        Stack lifo = new Stack();
        String[] splits = sentence.split(" ");
        int tokencount = splits.length;

        // Let's add some items to it
        for (int i = 0; i < tokencount ; i++)
        {
        	System.out.println("Pushing "+splits[i]);
                lifo.push ( new String(splits[i]) );
        }

        // Last in first out means reverse order
        while ( !lifo.isEmpty() )
        {
                System.out.print ( lifo.pop() );
                System.out.print ( " | " );
        }

        // Empty, let's lift off!
        System.out.println (" LIFT-OFF!");
	}
	
	public void extractingSentimentsAspects(String sentence)
	{
			this.ExtractNounSubtree(sentence);
			this.ExtractVerbSubtree(sentence);		
	}


	private static void ExtractVerbSubtree(String sentence) {
//		if (!sentence.startsWith("(TOP")) 
//		{
//			System.out.println(" Invalid Parse Structure");
//			//return null;
//		}
		
		int totalnounphrases = StringUtils.countMatches(sentence, "(VP");
		VerbPhrases = new String[totalnounphrases];	
		int indexNP = sentence.indexOf("(VP");
		
			String nounSubtree = sentence; 
			Stack lifo = new Stack();
			String[] splits = null;
			int tokencount = -1;
			int countopenbraces = -1;
			int countclosedbraces = -1;
			
			
			while(countverbphrases != (totalnounphrases))
			{	
				if(indexNP != -1)
					nounSubtree = nounSubtree.substring(indexNP, nounSubtree.length());
				else
					break;
				
	            splits = nounSubtree.split(" ");	    
	            tokencount = splits.length;
		    
			    for (int i = 0; i < tokencount ; i++)
				{
				    	if(splits == null)
				    		break;	
				    	
				    	countopenbraces = StringUtils.countMatches(splits[i], "(");				      	
				    	while(countopenbraces != 0 )
				    	{
				    		lifo.push ("(");
				    		countopenbraces-- ;
				    	}
				    	
				    	countclosedbraces = StringUtils.countMatches(splits[i], ")");				      	
				    	while(countclosedbraces != 0 )
				    	{
				    		lifo.pop();
				    		if(lifo.isEmpty())
				    		{
				    			System.out.println("Now Empty"+splits[i]);
				    			indexNP = nounSubtree.indexOf(splits[i]);			
				    			
				    	        
				    	        if(indexNP != -1)
				    	        {
				    	        	indexNP = indexNP + splits[i].length();
				    	        	System.out.println(" My Verb Phrases "+nounSubtree.substring(0,indexNP));
				    	        	
//				    	        	int totalnounphrasesinter = StringUtils.countMatches(nounSubtree.substring(0,indexNP), "(VP");
//				    	        	if(totalnounphrasesinter > 1)
//				    	        		new ParseTrees().ExtractVerbSubtree(nounSubtree.substring(0,indexNP));	
				    	        	
				    	        	VerbPhrases[countverbphrases] = nounSubtree.substring(0,indexNP);		    	        	
				    	        	countverbphrases++;
				    	        	nounSubtree = nounSubtree.substring(indexNP, nounSubtree.length());
				    	        	indexNP = nounSubtree.indexOf("(VP");
//				    	        	if(indexNP != -1)
//				    	        		nounSubtree = nounSubtree.substring(indexNP, nounSubtree.length());
				    	        	splits = null;
				    	        	break;
				  
				    	        	
				    	        }
				    			
				    		}
				    		countclosedbraces-- ;
				    	}
				      	
				    }
		
	  }
	}


	public static void ExtractNounSubtree(String sentence) {		
		
		
		
		if (!sentence.startsWith("(TOP")) 
		{
			System.out.println(" Invalid Parse Structure");
			//return null;
		}
		
		int totalnounphrases = StringUtils.countMatches(sentence, "(NP");
		NounPhrases = new String[totalnounphrases];	
		int indexNP = sentence.indexOf("(NP");
		
			String nounSubtree = sentence; 
			Stack lifo = new Stack();
			String[] splits = null;
			int tokencount = -1;
			int countopenbraces = -1;
			int countclosedbraces = -1;
			
			
			while(countnounphrases != (totalnounphrases))
			{	
				if(indexNP != -1)
					nounSubtree = nounSubtree.substring(indexNP, nounSubtree.length());
				else
					break;
				
	            splits = nounSubtree.split(" ");	    
	            tokencount = splits.length;
		    
			    for (int i = 0; i < tokencount ; i++)
				{
				    	if(splits == null)
				    		break;	
				    	
				    	countopenbraces = StringUtils.countMatches(splits[i], "(");				      	
				    	while(countopenbraces != 0 )
				    	{
				    		lifo.push ("(");
				    		countopenbraces-- ;
				    	}
				    	
				    	countclosedbraces = StringUtils.countMatches(splits[i], ")");				      	
				    	while(countclosedbraces != 0 )
				    	{
				    		lifo.pop();
				    		if(lifo.isEmpty())
				    		{
				    			System.out.println("Now Empty"+splits[i]);
				    			indexNP = nounSubtree.indexOf(splits[i]);			
				    			
				    	        
				    	        if(indexNP != -1)
				    	        {
				    	        	indexNP = indexNP + splits[i].length();
				    	        	System.out.println(" My Noun Phrases "+nounSubtree.substring(0,indexNP));
				    	        	
				    	        	int countopenbracesinter = StringUtils.countMatches(nounSubtree.substring(0,indexNP),"(");;
				    	        	
				    	        	NounPhrases[countnounphrases] = nounSubtree.substring(0,indexNP);		    	        	
				    	        	countnounphrases++;
				    	        	nounSubtree = nounSubtree.substring(indexNP, nounSubtree.length());
				    	        	indexNP = nounSubtree.indexOf("(NP");
//				    	        	if(indexNP != -1)
//				    	        		nounSubtree = nounSubtree.substring(indexNP, nounSubtree.length());
				    	        	splits = null;
				    	        	break;
				  
				    	        	
				    	        }
				    			
				    		}
				    		countclosedbraces-- ;
				    	}
				      	
				    }
		
	  }
   }
}
