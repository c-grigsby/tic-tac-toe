/*
 * Name: Christopher Grigsby
 * Date: 10/23/20
 * Assignment: EOM5(TicTacToe)
 * Pseudocode: 
 * 
 * PROGRAM TicTacToe: 
 * 
 * Initialize all variables (BOARD_FULL, PLAYER1, PLAYER2, row, column, count, winner, board)
 * 
 * Display msg "Welcome to TicTacToe" 
 * 
 * Call Method displayBoard
 * 
 * Repeat while no winner and count is less than BOARD_FULL
 *         
 *         Repeat while row or column input is less than 1 or greater than 3
 *                
 *                 Prompt player 1 for column and row 
 *                 
 *         increment count 
 *         board  is updated by Call Method addPlayerPosition 
 *         winner is evaluated by Call Method threeInARow
 *         Call Method displayBoard 
 *                 
 *         If no winner and count is less than BOARD_FULL
 *                         
 *                 Repeat while row or column input is less than 1 or greater than 3
 *                 		   Prompt player 2 for column and row 
 *                 
 *                 increment count 
 *                 board  is updated by Call Method addPlayerPosition 
 *                 winner is evaluated by Call Method threeInARow
 *                 Call Method displayBoard    
 *                
 *         If winner is found 
 *                 Display msg "Winner"
 *         Otherwise 
 *                 Display msg "No Winner"  
 */

/**
 * Program Description: 
 * This program enables the user/s to play a game of TicTacToe
 */

import java.util.*; 

public class TicTacToe 
{
	public static void main(String[] args) 
	{
		//Initialize all variables 
		Scanner in = new Scanner(System.in); 
		final int BOARD_FULL = 9; 
		final int PLAYER1 = 1; 
		final int PLAYER2 = 2; 
		int row = 0; 
		int column = 0; 
		int count = 0; //number of turns 
		boolean winner = false; 
		
		String[][] board = new String [3][3]; 
		
		
		//Prompt user for information 
		System.out.println("Welcome to TicTacToe!\n"); 
		displayBoard(board);  //Call Method displayBoard
		
		while (!winner && count < BOARD_FULL) //loop while game is not over
		{
			
			do  //input validation for array index 
			{
				System.out.print("Player 1, choose a row and column: "); //Prompt for player1 move
				row = in.nextInt(); 
				column = in.nextInt(); 
				
			}
			while (row > 3 || row < 1 || column > 3 || column < 1); 
			
			count++; //increment each turn 
			board = addPlayerPosition(row, column, board, PLAYER1); //call Method addPlayerPosition 
			winner = threeInARow(board);  //call Method threeInAROW
			displayBoard(board);          //call Method displayBoard
		
			if (!winner && count < BOARD_FULL)
			{
				do //input validation for array index
				{
					System.out.print("Player 2, choose a row and column: "); //Prompt for player2 move
					row = in.nextInt(); 
					column = in.nextInt();  
				}
				while (row > 3 || row < 1 || column > 3 || column < 1); 
			
				count++; //increment after each turn 
				board = addPlayerPosition(row, column, board, PLAYER2);  //call Method addPlayerPosition 
				winner =threeInARow(board);  //call Method threeInARow 
				displayBoard(board);         //call Method displayBoard 
			}
		}
		
		if (winner) //display winner or no winner 
		{
			System.out.println("WINNER, WINNER CHICKEN DINNER!"); 
		}
		else
		{
			System.out.print("No Winner, CATS!");
		}
		
	}//main
	
	/**
	 * Method displayBoard - Displays a String Array
	 * @param board - a String array representing a game of TicTacToe
	 * return - no return type
	 */
	
	public static void displayBoard(String [][] board) 
	{
		for (int x = 0; x < board.length; x++) //loop to remove any null from display 
		{
			for (int y = 0; y < board[0].length; y++)
			{
				if (board[x][y] == null) 
				{
					board[x][y] = " "; 
				}
			}
		}
		
		String [][] gameBoard =  //frame for board 
			{		
			    {"* ", "|",         "1 ",  "|",         "2 ",  "|",         "3 ",  "|", " "}, 
				{"--", "-",         "--",  "-",         "--",  "-",         "--",  "-", "-"},
				{"1 ", "|",  board[0][0], " |",  board[0][1], " |",  board[0][2], " |", " "},
				{"--", "-",         "--",  "-",         "--",  "-",         "--",  "-", "-"},
				{"2 ", "|",  board[1][0], " |",  board[1][1], " |",  board[1][2], " |", " "},  
				{"--", "-",         "--",  "-",         "--",  "-",         "--",  "-", "-"},
				{"3 ", "|",  board[2][0], " |",  board[2][1], " |",  board[2][2], " |", " "},
			}; 
		
		for (int i = 0; i < gameBoard.length; i++) //display current gameBoard
		{
			for (int j = 0; j < gameBoard[0].length; j++)
			{
				System.out.print(gameBoard[i][j]);
			}
			System.out.println(); 
		}
		System.out.println("\n"); 
		
	}//displayBoard
	
	/**
	 * Method addPlayerPosition - obtains a players position from an array, 
	 * and updates a String array representing the board with this position 
	 * @param row - an integers representing a player's row position 
	 * @param column - an integer representing a player's column position
	 * @param board - a String array containing the playing board
	 * @param counter - an integer that determines which player's turn 
	 * @return - an updated version of the String array with the player's position 
	 */
	
	public static String[][] addPlayerPosition(int row, int column, String[][] board, int player)
	{
		final int PLACEMENT_ADJUSTMENT = -1; 
		 
		int playerRow; 
		int playerColumn; 
		
		playerRow = row + PLACEMENT_ADJUSTMENT;        //adjust index from display board 
		playerColumn = column + PLACEMENT_ADJUSTMENT;  //adjust index from display board 
		
		
		if (player == 1) //player1 is O player2 is X
		{
			board[playerRow][playerColumn] = "O"; 
		}
		else 
		{
			board[playerRow][playerColumn] = "X"; 
								
		}
		return board; 
	}//addPlayerPosition 
	
	/**
	 * method threeInARow - examines the elements of a String array to discover if there are 
	 * three of a kind horizontally, vertically, or diagonally 
	 * @param ticTac
	 * @return - a boolean value of true if there are three of a kind elements, otherwise false 
	 */
	public static boolean threeInARow(String[][] ticTac)
	{
		String rowTotal = ""; 
		String columnTotal = ""; 
		String diagonalTotal = ""; 
		String diagonalTotal2 = ""; 
		int column = 0; 
		boolean winner = false; 
		
		for (int i = 0; i < ticTac.length; i++) //ticTac rows
		{
			diagonalTotal = diagonalTotal + ticTac[i][i]; //checks diagonal pattern
			
			
			for (int j = 0; j < ticTac[0].length; j++)  //ticTac columns 
			{
			rowTotal = rowTotal + ticTac[i][j];  		//checks rows totals  
			columnTotal = columnTotal + ticTac[j][i];   //checks columns totals 
			}
			
			diagonalTotal2 = ticTac[0][2] + ticTac[1][1] + ticTac[2][0]; //checks 2nd diagonal 
			
			if (rowTotal.equals("XXX") || rowTotal.equals("OOO") ||  //checks for winner 
				columnTotal.equals("XXX") || columnTotal.equals("OOO") ||
				diagonalTotal.equals("XXX") || diagonalTotal.equals("OOO") ||
				diagonalTotal2.equals("XXX") || diagonalTotal2.equals("OOO"))
			{
					winner = true; 
					return winner; 
			}
			else 
			{
				rowTotal = ""; 
				columnTotal = ""; 
			} 	
		}
		System.out.println(); 
		return winner; 
		
	}//threeInARow
	
}//class
