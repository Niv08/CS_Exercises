/*
Assignment number : 09
File Name : LanguageModel.java
Name (First Last) : Niv Shani
Student ID : 311361661
Email : niv.shani@post.idc.ac.il
*/

package languagemodel;

import std.StdIn;
import std.StdOut;

import java.util.HashMap;
import java.util.LinkedList;

public class LanguageModel {

	// The length of the moving window
	private int windowLength; 
	// The map where we manage the (window, LinkedList) mappings 
	private HashMap<String, LinkedList<CharProb>> probabilities;

	/**
	 * Creates a new language model, using the given window length.
	 * @param windowLength
	 */
	public LanguageModel(int windowLength) {
		this.windowLength = windowLength;
		probabilities = new HashMap<String, LinkedList<CharProb>>();
	}

	/**
	 * Builds a language model from the text in standard input (the corpus).
	 */
	public void train() {
	    String window = "";
		char c;

        for (int i = 0; i < windowLength; i++) {
            window += StdIn.readChar();
        }

        while (!StdIn.isEmpty()) {
            c = StdIn.readChar();

            if (probabilities.get(window) == null) {
                probabilities.put(window, new LinkedList<CharProb>());
            }

            LinkedList<CharProb> currentList = probabilities.get(window);
            calculateCounts(currentList, c);

            window += c;
            window = window.substring(1);
        }

        for (LinkedList<CharProb> currentList : probabilities.values()) {
            calculateProbabilities(currentList);
        }
	}
		
	// If the given character is found in the given list, increments its count;
    // Otherwise, constructs a new CharProb object and adds it to the given list.
	private void calculateCounts(LinkedList<CharProb> probs, char c) {

	    int index = 0;
	    while (index < probs.size()) {
	        if (probs.get(index).chr == c) {
                probs.get(index).count++;
                return;
            }
            index++;
        }

        probs.add(new CharProb(c));
	    return;

	}
	
	// Calculates and sets the probabilities (p and cp fields) of all the
	// characters in the given list.
	private void calculateProbabilities(LinkedList<CharProb> probs) {
        int totalCharsNumber = 0;
        for (int i = 0; i < probs.size(); i++) {
            totalCharsNumber += probs.get(i).count;
        }

        probs.get(0).CharProb(totalCharsNumber);
        probs.get(0).cp = probs.get(0).p;

        for (int i = 1; i < probs.size(); i++) {
            probs.get(i).CharProb(totalCharsNumber);
            probs.get(i).cp = probs.get(i-1).cp + probs.get(i).p;
        }
	}	

	/**
	 * Returns a string representing the probabilities map.
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (String key : probabilities.keySet()) {
			LinkedList<CharProb> keyProbs = probabilities.get(key);
			str.append(key + " : " + keyProbs + "\n");
		}
		return str.toString();
	}		
	
	// Learns the text that comes from standard input,
	// using the window length given in args[0],
	// and prints the resulting map. 
	public static void main(String[] args) {

	   int windowLength = StdIn.readInt();
	   StdIn.setInput("test.txt");
//	   int windowLength = Integer.parseInt(args[0]);
	   // Constructs a learning model
	   LanguageModel lm = new LanguageModel(windowLength);
	   // Builds the language model
	   lm.train();
	   // Prints the resulting map
	   System.out.println(lm);
	}
}
