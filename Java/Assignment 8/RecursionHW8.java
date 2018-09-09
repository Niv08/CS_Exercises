/*
Assignment number : 08
File Name : RecursionHW8.java
Name (First Last) : Niv Shani
Student ID : 311361661
Email : niv.shani@post.idc.ac.il
*/

import std.*;
public class RecursionHW8 {

    public static void main(String[] args) {
//        test1(); //Tests the add function
//        test2(); //Tests the int2Bin function
        test3(); //Tests the string2Int function
//        test4(); //Tests the isPalindrome function
//        test5(); //Tests the sierpinski function
    }


    /**
     * Tests the add function.
     */
    public static void test1() {
        StdOut.println("5 + 8 = " + add(3,8)); //11
        StdOut.println("5 + 0 = " + add(5,0)); //5
        StdOut.println("13 + 7 = " + add(13,7)); //20
        StdOut.println();
    }

    /**
     * Tests the int2Bin function.
     */
    public static void test2() {
        StdOut.println(int2Bin(5)); //101
        StdOut.println(int2Bin(35)); //100011
        StdOut.println(int2Bin(26)); //11010
        StdOut.println();
    }

    /**
     * Tests the string2Int function.
     */
    public static void test3() {
        StdOut.println(string2Int("2053")); //2053 as an integer
        StdOut.println(string2Int("2053") + 7); //2060
        StdOut.println(string2Int("1000") + 124); //1124
//        StdOut.println(string2Int("205f3")); //Exception
        StdOut.println();
    }

    /**
     * Tests the isPalindrome function.
     */
    public static void test4() {
        StdOut.println(isPalindrome("madam")); //true
        StdOut.println(isPalindrome("avid diva")); //true
        StdOut.println(isPalindrome("dices")); //false
        StdOut.println(isPalindrome("adam")); //false
        StdOut.println();
    }

    /**
     * Tests the sierpinski triangle function.
     */
    public static void test5() {
        sierpinski(8);
    }

    /**
     * recursively calculate the sum of (a + b).
     *
     * @param a integer
     * @param b integer
     * @return the sum of (a + b)
     */
    public static int add(int a, int b) {
        if ((a == 0) && (b != 0)) {
            return 1 + add(a, b - 1);
        }
        else if ((b == 0) && (a != 0)) {
            return 1 + add(a - 1, b);
        }
        else if ((a == 0) && (b == 0)) return 0;

        return 1 + 1 + add(a - 1, b -1);
    }

    /**
     * wrapper for the int2Bin() function.
     *
     * @param n integer
     * @return the binary representation of the number as a string.
     */
    public static String int2Bin(int n) {
        return int2Bin(n, "");
    }

    /**
     * recursively calculates and builds the
     * binary representation of the number as a string.
     *
     * @param n integer, s (accumulator string)
     * @return the binary representation of the number as a string.
     */
    public static String int2Bin(int n, String s) {
        if (n == 0) return s;

        s = n % 2 + s;
        return int2Bin(n / 2, s);
    }

    /**
     * wrapper for the string2Int() function.
     *
     * @param str string
     * @return the given string as an integer
     */
    public static int string2Int(String str) {
        return string2Int(str, 0);
    }


    /**
     * recursively calculates and sums the integer from the
     * given string. Assumes the string contains digits only.
     * If any of the characters in the string is not a digit,
     * the function throws a numberFormatException exception.
     *
     * @param str string
     * @param n int (accumulator integer)
     * @return the integer that the given string represents.
     */
    public static int string2Int(String str, int n) {

        if (str.length() == 0) {
            return n;
        }

        if ((str.charAt(0) < '0') || (str.charAt(0) > '9'))
            throw new NumberFormatException("One of the characters in the string is not a digit.");

        int c = str.charAt(0) - 48;
        n += c * (Math.pow(10, str.length() - 1));
        str = str.substring(1);
        return string2Int(str, n);
    }

    /**
     * recursively checks if the given string is a palindrome.
     *
     * @param str string
     * @return true if the given string is a palindrome, false otherwise
     */
    public static boolean isPalindrome(String str) {

        if (str.length() == 1)
            return true;

        int start = 0;
        int end = str.length() - 1;

        if (str.charAt(start) == str.charAt(end)) {
            str = str.substring(start + 1, end);
            return isPalindrome(str);
        }
        else
            return false;
    }

    /**
     * wrapper for the sierpinski triangle function.
     * sets up a 1 X 1 canvas.
     *
     * @param n integer - the triangle's depth
     */
    public static void sierpinski(int n) {
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);
        sierpinski(0,0, 1, n);
    }

    /**
     * recursively draws the sierpinski triangle of depth n.
     *
     * @param x1 double - starting point x vertex
     * @param y1 double - starting point y vertex
     * @param a double - the triangle's side length (equilateral triangle)
     * @param n integer - the triangle's depth
     */
    public static void sierpinski(double x1, double y1, double a, int n) {

        if (n == 0) {
            return;
        }

        StdDraw.line(x1, y1, x1 + (0.5 * a), y1 + a);
        StdDraw.line(x1 + (0.5 * a), y1 + a, x1 + a, y1);
        StdDraw.line(x1, y1, x1 + a, y1);

        a = 0.5 * a;

        sierpinski(x1, y1, a, n - 1);
        sierpinski(x1 + a, y1, a, n - 1);
        sierpinski(x1 + (0.5 * a), y1 + a, a, n - 1);

    }
}
