/*
Assignment number : 02
File Name : RandomWalker.java
Name (First Last) : Niv Shani
Student ID : 311361661
Email : niv.shani@post.idc.ac.il
*/


public class RandomWalker {
	public static void main(String[] args) {

		//declaring n and recieving user input
		int n = Integer.parseInt(args[0]);

		//initializing variables for the robot steps
		int x = 0;
		int y = 0;
		int direction = 0;

		for (int i=1;i<=n;i++) {
			direction = (int)(Math.random()*3) +1;   //1: north 2:east 3:south 4:west
			if (direction == 1) y++;
			if (direction == 2) x++;
			if (direction == 3) y--;
			if (direction == 4) x--;
			System.out.println("("+x+","+y+")");
		}

		//calculating the Euclidean distance from the origin
		int distance = x*x + y*y;
		System.out.println("squared distance = "+distance);
	}
}