/*
Assignment number : 01
File Name : SinFunction.java
Name (First Last) : Niv Shani
Student ID : 311361661
Email : niv.shani@post.idc.ac.il
*/


public class SinFunction {
	
	public static void main (String[] args){
		
		//declaring Pi
		double pi = Math.PI;

		System.out.println("sin(0) = " + Math.sin((0*pi)));
		System.out.println("sin(1/4 PI) = " + Math.sin((0.25*pi)));
		System.out.println("sin(1/2 PI) = " + Math.sin((0.5*pi)));
		System.out.println("sin(3/4 PI) = " + Math.sin((0.75*pi)));
		System.out.println("sin(PI) = " + Math.sin(pi));
		System.out.println("sin(5/4 PI) = " + Math.sin((1.25*pi)));
		System.out.println("sin(3/2 PI) = " + Math.sin((1.5*pi)));
		System.out.println("sin(7/4 PI) = " + Math.sin((1.75*pi)));
		System.out.println("sin(2 PI) = " + Math.sin(2*pi));
	}	
}
