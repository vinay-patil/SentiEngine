package com.ideas.SentimentAnalysis.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Parser;



import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.Span;

public class OpenNLPIntegration {

//	static String review = "Mary was there last year.property is with lots of greenery and relaxing swimming pool , fully entertaining and relaxing place, "
//			+ " rooms are well appointed, with enough space and big balcony.. children play area is also good. i would "
//			+ " like to recommend this property to other ";
//
//	public static void main(String args[]) throws InvalidFormatException,
//			IOException {
//		OpenNLPIntegration opennlp = new OpenNLPIntegration();
//		opennlp.SentenceDetect(review);
//		System.out.println("Tokenize");
//		String[] tokens = opennlp.Tokenize(review);
//		System.out.println("Location Finder");
//		opennlp.findName(tokens);
//		System.out.println("POS tagging");
//		String POSString = opennlp.POSTag(review);
//		System.out.println("Parsing");
//		// String POSString = 
//				opennlp.Parse(review);
//
//	}

	private ConsoleDisplay consoleDisplay;
	public OpenNLPIntegration(ConsoleDisplay consoleDisplay) {
		this.consoleDisplay = consoleDisplay;
		// TODO Auto-generated constructor stub
	}

	public String[] SentenceDetect(String reviewtext) throws InvalidFormatException, IOException {
		// always start with a model, a model is learned from training data
		InputStream is = new FileInputStream("C:\\MySystem\\V5i\\Eclipse\\FunctionSpace\\JavaWordnet\\model\\en-sent.bin");
		SentenceModel model = new SentenceModel(is);
		SentenceDetectorME sdetector = new SentenceDetectorME(model);
		String sentences[] = sdetector.sentDetect(reviewtext);
//		System.out.println(sentences[0]);
//		System.out.println(sentences[1]);
		is.close();
		
		if(sentences.length > 1)
			this.consoleDisplay.DisplayOnConsole("Done with Sentence detection and seperation ...");
		
		System.out.println("           Currently scanning "+sentences.length+ " sentences .");
		return sentences;
	}

	public static String[] Tokenize(String reviewtext)
			throws InvalidFormatException, IOException {
		InputStream is = new FileInputStream(
				"C:\\MySystem\\V5i\\Eclipse\\FunctionSpace\\JavaWordnet\\model\\en-token.bin");

		TokenizerModel model = new TokenizerModel(is);
		Tokenizer tokenizer = new TokenizerME(model);
		String tokens[] = tokenizer.tokenize(reviewtext);

		// for (String a : tokens)
		// System.out.println(a);

		is.close();
		return tokens;
	}

	public static void findName(String[] tokens) throws IOException {
		InputStream is = new FileInputStream(
				"C:\\MySystem\\V5i\\Eclipse\\FunctionSpace\\JavaWordnet\\model\\en-ner-location.bin");

		TokenNameFinderModel model = new TokenNameFinderModel(is);
		is.close();

		NameFinderME nameFinder = new NameFinderME(model);
		Span nameSpans[] = nameFinder.find(tokens);

		for (Span s : nameSpans)
			System.out.println(s.toString());
	}

	public static String POSTag_old(String reviewtext) throws IOException {

		POSModel model = new POSModelLoader()
				.load(new File(
						"C:\\MySystem\\V5i\\Eclipse\\FunctionSpace\\JavaWordnet\\model\\en-pos-maxent.bin"));
		PerformanceMonitor perfMon = new PerformanceMonitor(System.err, "sent");
		POSTaggerME tagger = new POSTaggerME(model);

		ObjectStream<String> lineStream = new PlainTextByLineStream(
				new StringReader(reviewtext));
		POSSample sample = null;
		perfMon.start();
		String line;
		while ((line = lineStream.read()) != null) {

			String whitespaceTokenizerLine[] = WhitespaceTokenizer.INSTANCE
					.tokenize(line);
			String[] tags = tagger.tag(whitespaceTokenizerLine);
			sample = new POSSample(whitespaceTokenizerLine, tags);
			System.out.println(sample.toString());
			perfMon.incrementCounter();

			// InputStream is = new
			// FileInputStream("C:\\MySystem\\V5i\\Eclipse\\FunctionSpace\\JavaWordnet\\model\\en-token.bin");
			// TokenizerModel modeltokpos = new TokenizerModel(is);
			// Tokenizer tokenizer = new TokenizerME(modeltokpos);
			// Span[] tokens = tokenizer.tokenizePos(sample.toString());
			// for (Span a : tokens)
			// System.out.println(a);
		}

		perfMon.stopAndPrintFinalResult();
		return sample.toString();
	}
	
	public String POSTag(String reviewtext,POSModel model) throws IOException {

		//POSModel model = new POSModelLoader().load(new File("C:\\MySystem\\V5i\\Eclipse\\FunctionSpace\\JavaWordnet\\model\\en-pos-maxent.bin"));
		POSTaggerME tagger = new POSTaggerME(model);
		ObjectStream<String> lineStream = new PlainTextByLineStream(new StringReader(reviewtext));
		POSSample sample = null;
		
		
		PerformanceMonitor perfMon = new PerformanceMonitor(System.err, "sent");
		perfMon.start();
		String line;
		while ((line = lineStream.read()) != null) {

			String whitespaceTokenizerLine[] = WhitespaceTokenizer.INSTANCE
					.tokenize(line);
			String[] tags = tagger.tag(whitespaceTokenizerLine);
			sample = new POSSample(whitespaceTokenizerLine, tags);
			//System.out.println(sample.toString());
			perfMon.incrementCounter();

			
		}

		//perfMon.stopAndPrintFinalResult();
		return sample.toString();
	}
	
	
	public static void Parse(String reviewsentence) throws InvalidFormatException, IOException {
		
		InputStream is = new FileInputStream("C:\\MySystem\\V5i\\Eclipse\\FunctionSpace\\JavaWordnet\\model\\en-parser-chunking.bin");	 
		ParserModel model = new ParserModel(is);
		
		opennlp.tools.parser.Parser parser = ParserFactory.create(model);
	 
		String sentence = "Programcreek is a very huge and useful website.";
		opennlp.tools.parser.Parse topParses[] = ParserTool.parseLine(reviewsentence, parser, 1);
	 
		for (opennlp.tools.parser.Parse  p : topParses)
		{
			StringBuffer sb = new StringBuffer(reviewsentence.length() * 4);
			p.show(sb);
			//System.out.println(sb);
		}
		is.close();
	 
		/*
		 * (TOP (S (NP (NN Programcreek) ) (VP (VBZ is) (NP (DT a) (ADJP (RB
		 * very) (JJ huge) (CC and) (JJ useful) ) ) ) (. website.) ) )
		 */
	}

}
