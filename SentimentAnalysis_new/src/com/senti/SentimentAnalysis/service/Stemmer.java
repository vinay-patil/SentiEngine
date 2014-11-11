package com.ideas.SentimentAnalysis.service;


/*
File: Stemmer.java

Copyright 2010 - The Cytoscape Consortium (www.cytoscape.org)

Code written by: Layla Oesper
Authors: Layla Oesper, Ruth Isserlin, Daniele Merico

This library is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU Lesser General Public License
along with this project.  If not, see <http://www.gnu.org/licenses/>.
*/



/*

Porter stemmer in Java. The original paper is in

   Porter, 1980, An algorithm for suffix stripping, Program, Vol. 14,
   no. 3, pp 130-137,

See also http://www.tartarus.org/~martin/PorterStemmer

History:

Release 1

Bug 1 (reported by Gonzalo Parra 16/10/99) fixed as marked below.
The words 'aed', 'eed', 'oed' leave k at 'a' for step 3, and b[k-1]
is then out outside the bounds of b.

Release 2

Similarly,

Bug 2 (reported by Steve Dyrdahl 22/2/00) fixed as marked below.
'ion' by itself leaves j = -1 in the test for 'ion' in step 5, and
b[j] is then outside the bounds of b.

Release 3

Considerably revised 4/9/00 in the light of many helpful suggestions
from Brian Goetz of Quiotix Corporation (brian@quiotix.com).

Release 4

*/

import java.io.*;
import java.util.List;


import edu.mit.jwi.morph.WordnetStemmer;
import edu.sussex.nlp.jws.JWS;

import net.didion.jwnl.JWNL;
import net.didion.jwnl.data.POS;


/**
* Stemmer, implementing the Porter Stemming Algorithm
*
* The Stemmer class transforms a word into its root form.  The input
* word can be provided a character at time (by calling add()), or at once
* by calling one of the various stem(something) methods.
*/

public class Stemmer
{  
	
	//private static final POS NNS = null;
	
	public static void main(String[] args)
	{
		
		
	}
	
	public List<String> getStems(String word)
	{
		JWS ws = new JWS("C:\\Program Files (x86)\\WordNet\\","2.1");  
		WordnetStemmer stem =  new WordnetStemmer(ws.getDictionary());
		//System.out.println("test" + stem.findStems("reading",null) );
		List<String> stemmed = stem.findStems(word,null);		
		return stemmed;
	}
	
}
