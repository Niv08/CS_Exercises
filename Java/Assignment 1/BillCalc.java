/*
Assignment number : 01
File Name : BillCalc.java
Name (First Last) : Niv Shani
Student ID : 311361661
Email : niv.shani@post.idc.ac.il
*/



public class BillCalc {
	public static void main(String[] args) {

		// declare types and names for the variables
		int bill = Integer.parseInt(args[0]);
		int guests = Integer.parseInt(args[1]);
		double newBill;
		int eachPayment;

		//calculate the bill + tip amount (15%) and round the result
		newBill = Math.round((bill) * 1.15);

		//divide the new sum by the number of guests
		eachPayment = (int)(newBill / guests);

		//print the final payment for each guest
		System.out.println("Each guest should pay " + eachPayment + " Shekels");
	}
}