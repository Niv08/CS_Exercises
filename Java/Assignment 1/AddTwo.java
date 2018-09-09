/*
Assignment number : 01
File Name : AddTwo.java
Name (First Last) : Niv Shani
Student ID : 311361661
Email : niv.shani@post.idc.ac.il
*/

public class AddTwo {

	public static void main(String[] args) {
		
		//declare variable for the given numbers and their sum
		int a = Integer.parseInt(args[0]);
		int b = Integer.parseInt(args[1]);
		int sum;

		//calculate their sum
		sum = a + b;

		System.out.println(a + " + " + b +" = "+ sum);
	}
}
