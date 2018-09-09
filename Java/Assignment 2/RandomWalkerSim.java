/*
Assignment number : 02
File Name : RandomWalkerSim.java
Name (First Last) : Niv Shani
Student ID : 311361661
Email : niv.shani@post.idc.ac.il
*/

public class RandomWalkerSim {
	public static void main(String[] args) {

		//declaring n and T and recieving user input
		int n = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);

		//declaring the variable for distance, the distances' sum and average
		int distance = 0;
		int overallDistance = 0;
		double distanceAverage = 0;

		for (int k=1;k<=T;k++) {	//loop for the number of experiments
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
			}
			//calculating the Euclidean distance from the origin
			distance = x*x + y*y;
			overallDistance = overallDistance + distance;  //adding the distance to the total sum
			distance = 0;	//reset the value of distance
		}

		distanceAverage = (double)overallDistance / T ;
		System.out.println("After "+T+" trials, the average squared distance");
		System.out.println("of a robot that makes "+n+" random steps is "+distanceAverage);
	}
}