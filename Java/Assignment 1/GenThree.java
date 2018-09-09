/*
Assignment number : 01
File Name : GenThree.java
Name (First Last) : Niv Shani
Student ID : 311361661
Email : niv.shani@post.idc.ac.il
*/

public class GenThree {

	public static void main(String[] args) {
		
		//declare variables for the two given numbers, and assign our range
		int a = Integer.parseInt(args[0]);
		int b = Integer.parseInt(args[1]);
		int range = b-a;

		//generate the value of the 3 numbers
		int num1 = (int)(Math.random()*(range)) + a;
		int num2 = (int)(Math.random()*(range)) + a;
		int num3 = (int)(Math.random()*(range)) + a;
 
		//finding the minimal number among the 3
		int min = Math.min(num1,num2);
		min = Math.min(min,num3);

		//printing the results
		System.out.println(num1);
		System.out.println(num2);
		System.out.println(num3);
		System.out.println("The minimal generated number was "+ min);
	}
}
