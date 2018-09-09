/*
Assignment number : 03
File Name : Expand.java
Name (First Last) : Niv Shani
Student ID : 311361661
Email : niv.shani@post.idc.ac.il
*/

public class Expand {
	public static void main(String[] args) {
		String s = args[0];
		Parser.init(s); 	//assigning the user's input string into Parser class

		while (Parser.hasMoreChars()) {  		//while there is more chars in the string
			char x = Parser.nextChar();			// get the next letter
			int y = Parser.nextInt();			// get the next Int

			for (int i=0;i<y;i++) {			
				System.out.print(x);			//print in a line the letter (x)* y times
			}
		}
		System.out.println();
	}
}