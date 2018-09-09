/*
Assignment number : 04
File Name : WordSearch.java
Name (First Last) : Niv Shani
Student ID : 311361661
Email : niv.shani@post.idc.ac.il
*/

/**
 * Looks for a given word inside a letter matrix, horizontally or vertically.
 * 
 * hasWord: 	@params: board - two dimensional chars array
 * 					 word - String of the request word to look for
 * 			@return: true if the word is within the array, 
 * 					 horizontally or vertically. else false.
 */

public class WordSearch {

	public static void main(String[] args) {
		char[][] arr = {{'a','d','o','g'},
						{'f','h','i','u'},
						{'v','c','t','u'}};

		System.out.println(hasWord(arr, "dog"));
		System.out.println(hasWord(arr, "it"));
		System.out.println(hasWord(arr, "bog"));
	}

	public static boolean hasWord(char[][] board, String word) {

		int n = word.length();
		int rows = board.length;	//number of rows
		int cols = board[0].length; //number of columns

		if ((n>cols) && (n>rows)) {
			return false;
		}

		int i = 0;
		int k = 0;
		int counter = 0;
		boolean flag = false;

		for (int j=0;j<rows;j++) { 	//checks the board for horizontal matches

			while (i<cols) {

				if (word.charAt(k) == board[j][i]) {
					flag = true;
					counter++;
					k++;
				}
				else {
					flag = false;
					counter = 0;
				}

				if ((flag) && (counter == n)) {
					return true;
				}

				i++;

			} //end of a single row check

			i = 0;
			counter = 0;
			k = 0;
			flag = false;

		} 	//end of rows check

		// resetting the indicators to check the board for vertical matches
		int j = 0;
		k = 0;
		counter = 0;
		flag = false;

		for (i=0;i<cols;i++) {

			while (j<rows) {

				if (word.charAt(k) == board[j][i]) {
					flag = true;
					counter++;
					k++;
				}
				else {
					flag = false;
					counter = 0;
				}

				if ((flag) && (counter == n)) {
					return true;
				}

				j++;

			} //end of a single column check

			j = 0;
			counter = 0;
			k = 0;
			flag = false;

		} 	//end of columns check

		return false;
	}

}