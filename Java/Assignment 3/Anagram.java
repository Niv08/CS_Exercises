/*
Assignment number : 03
File Name : Anagram.java
Name (First Last) : Niv Shani
Student ID : 311361661
Email : niv.shani@post.idc.ac.il
*/

// A collection of methods for handling anagrams.
public class Anagram {
	public static void main(String args[]) {
	      // Tests the isAnagram function.
   		  System.out.println(isAnagram("silent","listen"));  // true
	      System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
	      System.out.println(isAnagram("Madam Curie","Radium came")); // true
	      // Tests the ramdomAnagram function.
	      System.out.println(randomAnagram("silent"));	     		     	
	   }  

	   // Returns true if the two given strings are anagrams, false otherwise.
	   public static boolean isAnagram(String str1, String str2) {
		   
	   		String string1 = preProcess(str1);
	   		String string2 = preProcess(str2);

	   		int n = string1.length();

	   		int counter1 = 0;
	   		int counter2 = 0;

	   		//comparing the shrinked strings' lengths
	   		if (n != string2.length()) {
	   			return false;
	   		}

	   		//counting how many times each character appears in each string, and comparing the results
	   		for (int i=0;i<n;i++) {

	   			char c = string1.charAt(i);
	   			int k = 0;
	   			int j = 0;

	   			while (k<n) {
	   				if (string1.charAt(k) == c) counter1++;
	   				k++;
	   			}

	   			while (j<n) {
	   				if (string2.charAt(j) == c) counter2++;
	   				j++;
	   			}

	   			if (counter1 != counter2) {
	   				return false;
	   			}
	   		}

	       return true;
	   }
	   
	   // Returns a preprocessed version of the given string: all the letter characters
	   // are converted to lower-case, and all the other characters are deleted. For example, 
	   // the string "What? No way!" becomes "whatnoway"
	   public static String preProcess(String str) {
		   
		   String newStr = "";
	       int i = 0;
	       int n = str.length()-1;

	       while (i<=n) {
	       		
	       		//checks if the next char is an English letter
	       		if (isLetter(str.charAt(i))) {

	       			//checks to see if it is an uppercase letter, if true - makes it lowercase
	       			if ((str.charAt(i) >= 'A') && (str.charAt(i) <= 'Z')) { 
	       			char lowerCased =  (char)(str.charAt(i) + 32);
	       			newStr = newStr + lowerCased;
	       			}

	       			else {
	       				newStr = newStr + str.charAt(i); 
	       			}
	       		}

	       		i++;
	       }  

	       return newStr;
	   }
	   
	   // Returns a random anagram of the given string. The random anagram consists of the same
	   // letter characters as the given string, arranged in a random order. The random anagram
	   // is not required to form a word in the English language.
	   public static String randomAnagram(String str) {

	   		int originalLength = str.length();
	   		String tempStr = "";
	   		String newStr = "";

	   		int i = 0;

	   		/* generating random index, adding the corresponding char to the new string, 
	   		and generating a new string without it */
	   		while (i < originalLength) {
	   			int index = (int)((str.length())*Math.random());
	   			newStr = newStr + str.charAt(index);

	   			for (int k=0;k < str.length() ;k++) {
	   				if (k != index) tempStr = tempStr + str.charAt(k);
	   			}

	   			str = tempStr;
	   			tempStr = "";
	   			i++;
	   		}

	       return newStr;
	   }
	   
	   // Returns true if the given character is an English letter ('a' to 'z' or 'A' to 'Z'), false otherwise.
		private static boolean isLetter(char c) {
    		if (((c >= 'a') && (c <= 'z')) || ((c >= 'A') && (c <= 'Z'))) {
    			return true;
    		}
    	return false;
		}
}
