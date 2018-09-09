/*
Assignment number : 04
File Name : VectorSimilarity.java
Name (First Last) : Niv Shani
Student ID : 311361661
Email : niv.shani@post.idc.ac.il
*/

/**
 * Calculating the similarity of two given vectors a=(a1,a2), b=(b1,b2).
 * The vector's inner product is: (a*b).
 * The vector's norms is: (||a|| = sqrt(a1*a1 + a2*a2)).
 * The vector's similarity is: inner product divided by the multiplied norms.
 * 
 * norm: @params: a - integers array (assumed to be the vector).
 * 		 @return: norm - the vector calculated norm.
 * 
 * innerProduct: @params: a,b - integers arrays.
 * 				 @return: innerProduct - the calculated innerProduct.
 * 
 * similarity:	@params: a.b - integers arrays.
 * 				@return: similarity - the calculated vectors similarity.
 * 
 *  strToArray: 	@params: str - a String array of numbers.
 *  				@return: a - Integers arrays of the numbers in str.
 */



public class VectorSimilarity {

	public static void main(String[] args) {
		// Uncomment the tester that you want to run:
		// test1();
		// test2();
		// test3();
		// test4();
		test5(args[0],args[1]);	
	}
	
	private static void test1() {
		// Tests the norm function.
		int[] a = {3, 4};
		int[] b = {2, 0, 5, 0, 0, 3};
		System.out.println(norm(a));
		System.out.println(norm(b));
	}
	
	private static void test2() {
		// Tests the innerProduct function.
		int[] a = {3, 4};
		int[] b = {5, 7};
		System.out.println(innerProduct(a,b));
	}
	
	private static void test3() {
		// Tests the similarity function.
		int[] a = {2,0};
		int[] b = {2,1};

		System.out.println(similarity(a,b));
	}
	
	private static void test4() {
		// Tests the strToArray parsing function. Does not test extreme or 
		// invalid values, since in this program we assume that the inputs are valid.
		MyArrays1.print(strToArray("4,3,5"));        // Should print 4 3 5
		MyArrays1.print(strToArray("7"));            // Should print 7
		MyArrays1.print(strToArray("4,0,0,3,0,5"));  // Should print 4 0 0 3 0 5
	}
	
	private static void test5(String inputStr1, String inputStr2) {
		// Test the full program, using two command line arguments.
		int[] arr1 = strToArray(inputStr1);
		int[] arr2 = strToArray(inputStr2);
		System.out.println(similarity(arr1, arr2));	
	}
	
	/** Returns the norm of the vector represented by the given array.
	The norm of a vector (a1, a2, ..., an) is defined as (a1^2 + a2^2 + ... + an^2). */
	public static double norm(int[] a) {
		
		int n = a.length;
		int norm = 0;

		for (int i=0;i<n;i++) {
			norm = norm + ((a[i])*(a[i]));
		}

		return Math.sqrt(norm);
	}
	
	/** Returns the inner product of the two vectors represented by the two given arrays.
	 * If the two arrays don't have the same length, returns -1.
	 * The inner product of two vectors (a1, a2, ..., an) and (b1, b2, ..., bn) is defined
	 * as a1*b1 + a2*b2 + ... + an*bn. */
	public static int innerProduct(int[] a, int[] b) {

		int n1 = a.length;
		int n2 = b.length;
		int innerProduct = 0;

		if (n1 != n2) {
			return -1;
		}

		for (int i=0;i<n1;i++) {
			innerProduct = innerProduct + ((a[i])*(b[i]));
		}

		return innerProduct;
	}
	
	/** Returns the similarity of the two vectors represented by the two given arrays.
	 * The similarity is defined as the inner product of the two vectors, divided by
	 * the multiplication of their norms. If the two arrays don't have the same length, returns -1. */
	public static double similarity(int[] a, int[] b) {
		int n1 = a.length;
		int n2 = b.length;
		double similarity = 0.0;

		double normsMulti = (norm(a))*(norm(b));

		if (n1 != n2) {
			return -1;
		}

		similarity = (double)(innerProduct(a,b))/(normsMulti);

		return similarity;
	}
	
	// Given a comma-separated string of the form "a1,a2,a3,...", returns the array of integers
	// (a1,a2,a3,...). Assumes (without checking) that each character ai in the given string is a single digit. 
	public static int[] strToArray(String str) {

		int n = str.length();
		int[] a = new int[(n+1)/2]; 
		int index = 0;

		for (int i=0;i<n;i=i+2) {
			char c = str.charAt(i);
			a[index] = c-48;
			index++;
		}

		return a;
	}		
}
