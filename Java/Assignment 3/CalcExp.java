/*
Assignment number : 03
File Name : CalcExp.java
Name (First Last) : Niv Shani
Student ID : 311361661
Email : niv.shani@post.idc.ac.il
*/

public class CalcExp {
	public static void main(String args[]) {
	      // Tests the exp function.
		  double x = 2;  // use other values, if you want
		  int N = 10;    // experiment with other values, to see how it effects the compuatation's accuracy.
		  System.out.println("e raised to the power of " + x + " according to Java: "+ Math.exp(x));
		  System.out.println("Same value, using my exp function with " + N + " steps: " + exp(x,N));
	}
	
	// Returns an approximation of the value of the constant e raised to the power of the given x.
	// The approximation's accuracy is determined by the given N. The higher N, the higher the accuracy.
	public static double exp(double x, int N) {
		 
		//initializing e and assigning it the first part of the formula;
		double e = 1.0;

		double originalX = x;
		double factorial = 1.0;

		for (int i=1;i<=N;i++) {
			//multiplying the last factorial by i (4! = 3!*4)
			factorial = factorial*i;

			e += (x/factorial);		//adding the sum to the overall E
			x = (x)*(originalX); 	//multiplying x by the original value
		}

		return e;
	}

}
