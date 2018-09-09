/*
Assignment number : 02
File Name : DamkaBoard.java
Name (First Last) : Niv Shani
Student ID : 311361661
Email : niv.shani@post.idc.ac.il
*/

public class DamkaBoard {
	public static void main(String[] args) {

		//declaring n and recieving user input
		int n = Integer.parseInt(args[0]);

		//declaring strings variables to be built along the loop and printed out
		String line = "";
		String altLine = "";

		String cube = "*"+" ";
		String reverseCube = " "+"*";

		for (int k=1;k<=n;k++) { 	//loop for the number of rows
			for (int i=1;i<=n;i++) { 	//loop for the number of astricks in a row
				line = line + cube;		
				altLine = altLine + reverseCube;   //building the lines
			}
			if (k%2 == 0) {		//deciding which line to print
				System.out.println(altLine);				
			}
			else {
				System.out.println(line);
			}
		line = "";			//reset the lines strings
		altLine = "";
		}
	}	
}