/*
Assignment number : 07
File Name : PolynomialDemo.java
Name (First Last) : Niv Shani
Student ID : 311361661
Email : niv.shani@post.idc.ac.il
//*/

package polynomials;

import std.*;

public class PolynomialDemo {

	public static void main(String args[]) {
        
		// Tests the constructor and the toString method.
//		test1();
//		test2();
//		test3();
//		test4();
		test5();
		// A general test that creates and manipulates some polynomials.
		 generalTest();
	}
	
	// Tests the constructor and the toString method.
	public static void test1() {	
		// A polynomial of degree = 2
		int[] p1CoeffArr = {1, -5, 3};
		Polynomial p1 = new Polynomial(p1CoeffArr);
		System.out.println(p1 + "\n");
		
		// A polynomial of degree = 4
		int[] p2CoeffArr = {-2, 0, 7, 5, 12};		
		Polynomial p2 = new Polynomial(p2CoeffArr);
		System.out.println(p2 + "\n");
		
		// A polynomial of degree = 0
		int[] p3CoeffArr = {3};		
		Polynomial p3 = new Polynomial(p3CoeffArr); 
		System.out.println(p3 + "\n");
		
		// A polynomial of degree = 0, with trailing zero coefficients
		int[] p4CoeffArr = {5, 0, 0};		
		Polynomial p4 = new Polynomial(p4CoeffArr);
		System.out.println(p4 + "\n");

		// A polynomial of degree = 0, with trailing zero coefficients
		int[] p5CoeffArr = {0, 0, 0};		
		Polynomial p5 = new Polynomial(p5CoeffArr); 
		System.out.println(p5 + "\n");

		StdOut.println();
	}

	//Tests the value methods
	public static void test2() {

		// p(x) = 3x^2 - 5x + 1
		int[] pCoefficients = {1, -5, 3};
		Polynomial p = new Polynomial(pCoefficients);

		//  q(x) = x^4 + 2x - 7
		Polynomial q = new Polynomial(new int[] {-7, 2, 0, 0, 1});

		StdOut.println(p.getCoefficient(1)); //should print -5;
		StdOut.println(p.value(1)); //should print -1;
		StdOut.println(p.value(2)); //should print 3;

		StdOut.println(q.value(2)); //should print 13;
		StdOut.println(q.value(0)); //should print -7

		double[] values = {2, 5, 1, 0, 3, 4};
		double[] a = p.value(values);
		//Should print the polynomial's values array: {3,51,-1,1,13,29};
		printArr(a);

		StdOut.println();
	}

	//Tests the multByScalar and plus methods
	public static void test3() {

		// p(x) = 3x^2 - 5x + 1
		int[] pCoefficients = {1, -5, 3};
		Polynomial p = new Polynomial(pCoefficients);

		// p2(x) = -3x^2 + 7x + 4
		int[] p2Coefficients = {4,7,-3};
		Polynomial p2 = new Polynomial(p2Coefficients);

		Polynomial q;
		Polynomial h;
		Polynomial m;

		q = p.multByScalar(3);
		h = p.plus(q);
		m = p.plus(p2);

		StdOut.println(q.toString()); //Should print 9x^2 - 15x + 3 => {3,-15,9}, 2
		StdOut.println(h.toString()); //Should print 12x^2 -20x +4 => {4,-20,12}, 2
		StdOut.println(m.toString()); //Should print 2x + 5 => {5,2}, 1

		StdOut.println();

	}

	//Tests the derivative method
	public static void test4() {

		// p2(x) = -3x^2 + 7x + 4
		int[] p2Coefficients = {4,7,-3};
		Polynomial p2 = new Polynomial(p2Coefficients);

		// q(x) = 10x^5 + 9x^4 + 2x^2 -4x + 2
		int[] qCoeff = {2,-4,2,0,9,10,0,0,0};
		Polynomial q = new Polynomial(qCoeff);

		Polynomial pTag = p2.derivative();
		Polynomial qTag = q.derivative();

		StdOut.println(pTag.toString()); //Should print -6x + 7 => {7,-6}, 1
		StdOut.println(qTag.toString()); //Should print 50x^4 + 36x^3 + 4x - 4 => {-4,4,0,36,50}, 4

		StdOut.println();

	}

	//Tests the xArr, max and min methods
	public static void test5() {

//		p2(x) = -3x^2 + 7x + 4
		int[] p2Coefficients = {4,7,-3};
		Polynomial p2 = new Polynomial(p2Coefficients);

		printArr(p2.xArr(-10, 10, 20)); //should print 21 points from -10 to 10
												// (inclusive) with an interval of 1
		StdOut.println(p2.min(p2.xArr(-10, 10, 20))); //should print -10
		StdOut.println(p2.max(p2.xArr(-10, 10, 20))); //should print 10

		StdOut.println();
	}

	public static void generalTest() {
		// p(x) = 3x^2 - 5x + 1
		int[] pCoefficients = {1, -5, 3};
		Polynomial p = new Polynomial(pCoefficients); 
		System.out.println("p(x) = " + p);
		System.out.println("p(2) = " + p.value(2) + "\n");

		Polynomial ptag = p.derivative(); 
		System.out.println("ptag(x) = " + ptag);
		System.out.println("ptag(2) = " + ptag.value(2)+ "\n");

		//  q(x) = x^4 + 2x - 7
		Polynomial q = new Polynomial(new int[] {-7, 2, 0, 0, 1}); 
		System.out.println("q(x) = " + q);
		System.out.println("pPlusq(x) = " + p.plus(q));
		
		// Plots the two functions
		p.plot(-10, 10, 100);
		ptag.plot(-10, 10, 100);

		StdOut.println();
	}

	//Prints a one-dimensional array
	public static void printArr(double[] a) {
		/**
		 * Prints a one-dimensional array (implemented for debugging needs).
		 * @param a The double array to print
		 */
		StdOut.print("{");
		for (int i = 0; i < a.length - 1; i++) {
			StdOut.print(a[i] + ", ");
		}
		StdOut.println(a[a.length - 1] + "}");
	}

}