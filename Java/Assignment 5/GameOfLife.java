/*
Assignment number : 05
File Name : GameOfLife.java
Name (First Last) : Niv Shani
Student ID : 311361661
Email : niv.shani@post.idc.ac.il
*/

/** A class that plays the Game of Life.
 *  Usage: "GameOfLife fileName"
 *  The file represents the initial board.
 *  The file format is described in the HW05 document.
 */
import std.*;

public class GameOfLife {

	public static void main(String[] args) {
		String fileName = args[0];
		// Uncomment the test that you want to execute, and re-compile.
		// (Run one test at a time).
//		test1(fileName);
//		test2(fileName);
//		  test3(fileName, 3);
		 play(fileName);
	}
	
	// Reads the data file and prints the initial board.
	private static void test1(String fileName) {
		int[][] board = read(fileName);
		print(board);	
	}
		
	// Reads the data file, and runs some test code that checks 
	// the count and cell functions.
	private static void test2(String fileName) {
		int[][] board = read(fileName);
				
		print(board);
		
		if (fileName.equals("src/lifeDataLine")) {
			//should print 2 and then 1
			StdOut.println("The number of living cells around the center cell is: " + count(board, 3, 3));
			StdOut.println("Therefore, on the next generation he will be " + cell(board, 3, 3));
		}
		
		if (fileName.equals("src/lifeDataSquare")) {
			//should print 3 and then 1
			StdOut.println("The number of living cells around the bottom right corner living cell is: " + count(board, 3, 3));
			StdOut.println("Therefore, on the next generation he will be " + cell(board, 3, 3));
		}
		
	}
		
	// Reads the data file, plays the game for Ngen generations, 
	// and prints the board at the beginning of each generation.
	private static void test3(String fileName, int Ngen) {
		int[][] board = read(fileName);
		for (int gen = 0; gen < Ngen; gen++) {
			StdOut.println("Generation " + gen + ":");
			print(board);
			board = evolve(board);
		}
	}
		
	// Reads the data file and plays the game, for ever.
	private static void play(String fileName) {
		int[][] board = read(fileName);
		while (true) {
			show(board);
			board = evolve(board);
		}
	}
	
	// Reads the data from the given fileName, uses the data to construct the initial board,
	// and returns the initial board. Live and dead cells are represented by 1 and 0, respectively.
	// To avoid dealing with extreme cases, constructs a board that has 2 extra rows and 2
	// extra columns, containing zeros. These will be the top and the bottom row, and the 
	// the leftmost and the rightmost columns. Thus the actual board is surrounded by a "frame" of zeros. 
	// You can think of this frame as representing the infinite number of dead cells that 
	// exist in every direction.
	private static int[][] read(String fileName) {
		StdIn.setInput(fileName);
		int rows = Integer.parseInt(StdIn.readLine());
		int cols = Integer.parseInt(StdIn.readLine());

		int[][] board = new int[rows+2][cols+2];  //add 2 rows and cols to pad the margins for later checks
		int rowIndex = 1;
		int colIndex = 1;
		
		while ((StdIn.hasNextLine()) && (rowIndex < rows+2)) {
			
			String line = StdIn.readLine();
			
			//if the line is empty -> go to the next line.
			//else -> check the characters in the line and fill the board
			if (line.equals("")) { 
				rowIndex++;
			}
			
			else {
				for (int k = 0; k < line.length(); k++) {		
					if (line.charAt(k) == 'x') { 
						board[rowIndex][colIndex] = 1;
					}
					colIndex++;
				}
				
				//resetting board indexes to begin a new line 
				colIndex = 1;
				rowIndex++;
			}
		}
		
		return board;
	}
	
	// Creates a new board from the given board, using the rules of the game.
	// Uses the cell(board,i,j) function to compute the new contents of each 
	// cell in the new board. Returns the new board.
	private static int[][] evolve(int[][] board){
		
		int[][] newBoard = new int[board.length][board[0].length];
		
		for (int i = 1; i < board.length -1; i++) {
			for (int j = 1; j < board[i].length -1; j++) {
				newBoard[i][j] = cell(board, i, j);
			}
		}
		
		return newBoard;
		
	}

	// Returns the contents of cell (i,j) in the next generation.
	// If the cell is alive (equals 1) and has fewer than two live neighbors, it dies (becomes 0).
	// If the cell is alive and has two or three live neighbors, it remains alive.
	// If the cell is alive and has more than three live neighbors, it dies.
	// If the cell is dead and and has three live neighbors, it becomes alive.
	// Other cells don't change. 
	// Assumes that i is at least 1 and at most the number of rows in the board - 1. 
	// Assumes that j is at least 1 and at most the number of columns in the board - 1. 
	// Uses the count(board,i,j) function to count the number of alive neighbors.
	private static int cell(int[][] board, int i, int j) {
		
		int livingNeighbors = count(board, i, j);
		int cellValue = board[i][j];
		
		if (cellValue == 0) {
			if (livingNeighbors == 3) {
				cellValue = 1;
			}
		}
		
		else {
			if ((livingNeighbors < 2) || (livingNeighbors > 3)) {
				cellValue = 0;
			}
			
			else {
				cellValue = 1;
			}
		}
		
		return cellValue;
	}
	
	// Counts and returns the number of living neighbors of the given cell.
	// Assumes that i is at least 1 and at most the number of rows in the board - 1. 
	// Assumes that j is at least 1 and at most the number of columns in the board - 1. 
	private static int count (int[][] board, int i, int j) {
		
		int counter = 0;
		int k = i-1;
		int l = j-1;
		
		for (k = i-1; k <= i+1; k++) {
			for (l = j-1; l <= j+1; l++) {
				if (!((k == i) && (l == j))) {
					if (board[k][l] == 1) counter++;
				}
			}
		}
		
		return counter;
	}
	
	
	// Prints the board. Living and dead cells are printed as 1 and 0, respectively.
    private static void print(int[][] arr) {
		for (int i = 1; i < arr.length -1; i++) {
			for (int j = 1; j < arr[i].length -1; j++) {
//				StdOut.print(arr[i][j] + " ");
				StdOut.printf("%-2s", arr[i][j]);
			}
			StdOut.println();
		}
		StdOut.println();
	}
		
    // Displays the board. Living and dead cells are represented by black and white squares, respectively.
    // Since we use the same canvas size for displaying boards of different sizes, we change the square 
    // sizes to fill the canvas: The smaller the board, the larger the squares.
    // This code should be treated as a black box -- no need to study or understand it.
	private static void show(int[][] board) {
		// StdDraw.setCanvasSize(1024, 1024);
		StdDraw.setCanvasSize(900, 900);
		int rows = board.length;
		int cols = board[0].length;
		// StdDraw.setCanvasSize(height * 10, width *10);
		StdDraw.setXscale(0, cols);
		StdDraw.setYscale(0, rows);
		StdDraw.show(100);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				int grey = 255 * (1 - board[i][j]);
				StdDraw.setPenColor(grey, grey, grey);
				StdDraw.filledRectangle(j + 0.5, rows - i - 0.5, 0.5, 0.5);
			}
		}
		StdDraw.show();
	}
}
