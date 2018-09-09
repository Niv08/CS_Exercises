/*
Assignment number : 07
File Name : Polynomial.java
Name (First Last) : Niv Shani
Student ID : 311361661
Email : niv.shani@post.idc.ac.il
*/

package polynomials;

import std.*;

public class Polynomial {
	
	private int[] a;    // The coefficient of x^i is stored in a[i]
	private int degree; // The degree of this polynomial


    public Polynomial() {
        this.a = new int[0];
        this.degree = 0;
    }

	/**
	 * Constructs a polynomial from the given array of coefficients.
	 * If the given coefficients include trailing zeros, ignores them.
	 * @param coeffArr The given coefficients. The coefficient of the 
	 *             zero's term is represented by coeffArr[0], and so on.
	 */
	public Polynomial(int[] coeffArr) {
		// Computes and sets the degree of this polynomial.
		// If the given coefficients include trailing zeros, ignores them.

		int i = coeffArr.length - 1;

		while (coeffArr[i] == 0) {
			if (i > 0) {
				i--;
			}
			else break;
		}

		int[] newArray = new int[i + 1];
		degree = i;
		System.arraycopy(coeffArr, 0, newArray, 0, i + 1);

		a = newArray;

	}
	
	/** Returns the degree of this polynomial.
	 *  @return The degree of this polynomial.
	 */
	public int getDegree() {
		return degree;
	}
	
	/**
	 * Returns the coefficients of this polynomial.
	 * @return The coefficients of this polynomial, as an int array.
	 */
	public int[] getCoeffArr(){
		// Constructs an array of the right size, copies all the coefficients of this polynomial into it,
		// and returns the array. This is done in order to protect the coefficients of this polynomial. 
		// (If we will simply return the a array, the user can destroy it).

		int[] coeffArr = new int[a.length];
		System.arraycopy(a, 0, coeffArr, 0, a.length);

		return coeffArr;
	}
	
	/**
	 * Returns the i'th coefficient of this polynomial. <br>
	 * If i is greater than the degree of this polynomial, returns 0.
	 * @param i The term's power.
	 * @return The i'th coefficient of this polynomial.
	 */
	public int getCoefficient(int i) {

		if ((i >= 0) && (i < a.length)){
			return getCoeffArr()[i];
		}
		else
			return 0;
	}
	
	/**
	 * Returns the value of this polynomial at the given point.
	 * @param x The value at which this polynomial is computed 
	 * @return The value of this polynomial at the given point.
	 */
	public double value(double x) {

		double val = 0.0;

		for (int i = a.length - 1; i > 0; i--) {
			double pow = x;
			for (int j = 1; j < i; j++) {
				pow *= x;
			}
			val += a[i] * pow;
		}

		return val + a[0];
	}
	
	/**
	 * Returns a vector of values of this polynomial,
	 * computed over the given vector of values.
	 * @param x The values at which this polynomial is computed 
	 * @return The values of this polynomial at the given points.
	 */
    public double[] value(double x[]) {

    	double[] valuesArray = new double[x.length];

		for (int i = 0; i < valuesArray.length; i++) {
			valuesArray[i] = this.value(x[i]);
		}

        return valuesArray;
    }
    
    /**
	 * Returns the multiplication of this polynomial by the given scalar value.
	 * @param c The scalar
	 * @return The multiplication of this polynomial by the given scalar, as a new polynomial
	 */
	public Polynomial multByScalar(int c) {

		int[] newCoeff = new int[degree + 1];
		System.arraycopy(a, 0, newCoeff, 0, degree + 1);

		for (int i = 0; i < newCoeff.length; i++) {
			newCoeff[i] *= c;
		}

		return new Polynomial(newCoeff);
	}
	
	/**
	 * Returns the polynomial resulting from the addition of this polynomial
	 * and the other polynomial.
	 * @param other The other polynomial which is added to this polynomial.
	 * @return The sum of this polynomial and the other one, as a new polynomial.
	 */
	public Polynomial plus(Polynomial other) {

		int max = a.length;

		if (other.getCoeffArr().length == max) {
			int[] newCoeff = new int[max];
			for (int i = 0; i < newCoeff.length; i++) {
				newCoeff[i] = a[i] + other.getCoefficient(i);
			}
			return new Polynomial(newCoeff);
		}

		else if (other.getCoeffArr().length > max) {
			max = other.getCoeffArr().length;
			int[] newCoeff = new int[max];
			int[] extendedArr = new int[max];
			System.arraycopy(a, 0, extendedArr, 0, a.length);

			for (int i = 0; i < newCoeff.length; i++) {
				newCoeff[i] = extendedArr[i] + other.getCoefficient(i);
			}

			return new Polynomial(newCoeff);
		}

		else {
			int[] newCoeff = new int[max];
			int[] extendedArr = new int[max];
			System.arraycopy(other, 0, extendedArr, 0, other.getCoeffArr().length);

			for (int i = 0; i < newCoeff.length; i++) {
				newCoeff[i] = a[i] + extendedArr[i];
			}
			return new Polynomial(newCoeff);
		}
	}
	
	/**
	 * Returns the first derivative of this polynomial.
	 * @return The first derivative of this polynomial, as a new polynomial
	 */
	public Polynomial derivative() {

		int[] newCoeff = new int[degree];
		System.arraycopy(a, 1, newCoeff, 0, degree);

		for (int i = newCoeff.length - 1; i > 0; i--) {
			newCoeff[i] *= i + 1;
		}

		return new Polynomial(newCoeff);
	}
	
	/**
	 * Displays the graph of this polynomial, computed over the given range of values.
	 * @param xMin The left-most x value
	 * @param xMax The right-most x value
	 * @param nSegments The number of line segments used to approximate the graph
	 */
	public void plot(int xMin, int xMax, int nSegments) {

		double[] dots = xArr(xMin, xMax, nSegments); //creates an array of x values
		double[] values = value(dots); //creates an array of y values

		StdDraw.setXscale(xMin, xMax);
		StdDraw.setYscale(min(values), max(values));

		for (int i = 0; i < nSegments - 2; i++) {
			StdDraw.line(dots[i], values[i], dots[i + 1], values[i + 1]);
		}

	}
	
	/** Returns a string representation of this polynomial.
	 * @return This polynomial as a string of the form an*x^n + ... + a1*x + a0
	 */

	public String toString() {

		String s = "";
		int N = a.length - 1;

		if (N == 0) {
			if (a[0] > 0) s += a[0];
			else if (a[0] < 0) s += " - " + a[0];
			else s += 0;
		}

		else if (N == 1) {

			if (getCoefficient(1) > 0) {
				if (getCoefficient(1) != 1) s += getCoefficient(1) + "x";
				else s += "x";
			}
			else if (getCoefficient(1) < 0) {
				if (getCoefficient(1) != -1) s += " - " + (getCoefficient(1)*(-1)) + "x";
				else s += "- x";
			}

			if (getCoefficient(0) > 0) s += " + " + getCoefficient(0);
			else if (getCoefficient(0) < 0) s += " - " + (getCoefficient(0)*(-1));

		}

		else {
			if (getCoefficient(N) > 0) {
				if (getCoefficient(N) != 1) s += getCoefficient(N);
				s += "x^" + N;
			}
			else if (getCoefficient(N) < 0) {
				if (getCoefficient(N) == -1) s += "- x^" + N;
				else s += " - " + (getCoefficient(N)*(-1)) + "x^" + N;
			}

			for (int i = N - 1; i > 1; i--) {
				if (getCoefficient(i) > 0) {
					if (getCoefficient(i) != 1) s += " + " + getCoefficient(i) + "x^" + i;
					else s += " + x^" + N;
				}
				else if (getCoefficient(i) < 0) {
					if (getCoefficient(i) != -1) s += " - " + (getCoefficient(i)*(-1)) + "x^" + i;
					else s += " - x^" + N;
				}
			}

			if (getCoefficient(1) > 0) {
				if (getCoefficient(1) != 1) s += " + " + getCoefficient(1) + "x";
				else s += " + x";
			}
			else if (getCoefficient(1) < 0) {
				if (getCoefficient(1) != -1) s += " - " + (getCoefficient(1)*(-1)) + "x";
				else s += " - x";
			}

			if (getCoefficient(0) > 0) s += " + " + getCoefficient(0);
			else if (getCoefficient(0) < 0) s += " - " + (getCoefficient(0)*(-1));
		}

		return s;
	}
	
    // Returns an array that represent N equally-spaced
    // points between a and b (inclusive)
    public static double[] xArr(double a, double b, int N) {

		double d = (b - a) / (N); //the distance between each two points
		double[] points = new double[N + 1];

		points[0] = a;

		for (int i = 1; i < N + 1; i++) {
			points[i] = points[i - 1] + d;
		}

        return points;
    }
    
    // Returns the minimum value in the given array
	public static double min(double arr[]) {
		double min = arr[0];

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] <= min) {
				min = arr[i];
			}
		}
    	return min;
    }

    // Returns the maximum value in the given array
	public static double max(double arr[]) {
		double max = arr[0];

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] >= max) {
				max = arr[i];
			}
		}
		return max;
    }
}
