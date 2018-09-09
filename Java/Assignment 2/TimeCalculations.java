/*
Assignment number : 02
File Name : TimeCalculations.java
Name (First Last) : Niv Shani
Student ID : 311361661
Email : niv.shani@post.idc.ac.il
*/

public class TimeCalculations {
	public static void main(String[] args) {

		//recieving inputs HH:MM and m
		String[] parts = args[0].split(":");
		String strHours = parts[0];
		String strMinutes = parts[1];

		int hours = Integer.parseInt(strHours);
		int minutes = Integer.parseInt(strMinutes);
		int m = Integer.parseInt(args[1]);

		//checking for valid inputs
		if (((hours < 0) || (hours > 23)) ||
				((minutes < 0) || (minutes > 59)) ||
					(m < 0)) {
						System.out.println("Invalid input");
					}
		else {
			//calculating the new timestamp
			hours = hours + (m/60);
			if (hours > 23) hours = hours - 24;
			minutes = minutes + (m%60);
			if (minutes > 59) {
				minutes = minutes - 60;
				hours++;
			}

			//declaring a variable to store AM/PM
			String time = "AM";

			if (hours > 12) {
				time = "PM";
				hours = hours - 12;
			}

			//printing the new timestamp
			System.out.print(hours+":");
			if (minutes < 10) {
				System.out.print("0"+minutes+" "+time);
			}
			else {
				System.out.println(minutes+" "+time);
			}
		}
	}
}