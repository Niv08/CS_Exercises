/*
Assignment number : 02
File Name : CalcPI.java
Name (First Last) : Niv Shani
Student ID : 311361661
Email : niv.shani@post.idc.ac.il
*/

public class CalcPI {
	public static void main(String[] args) {

		//declaring variables and recieving the use input
		int n = Integer.parseInt(args[0]);
		double numUp = -1.0;
		double numDown = 3.0;
		double termsSum = 0;
		double pi;

		//calculating the terms sum
		for (int i=1; i<n; i++) {
			termsSum = termsSum + (numUp/numDown);
			numUp = -numUp;
			numDown = numDown + 2.0;
		}

		//calculating PI final approx. value 
		pi = 4*(1 + termsSum);

		System.out.println(pi);
	}
}