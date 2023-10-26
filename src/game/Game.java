package game;

import java.util.Scanner;

import minMax.MinMax;

public class Game implements GameInterface{

	static char[][] board = new char[3][3];
    String player;
    String computer;
    MinMax minMax;
    static Scanner sc = new Scanner(System.in);
    static final String ERROR_MESSAGE = "Invalid input!";
    static MinMax computerPlayer;
	
	@Override
	public void startGame() {
	    computer = "O";
	    player = "X";

	    computerPlayer = new MinMax(board, player.charAt(0), computer.charAt(0));

	    for (;;) {
	        boolean playerTurn = false;
	        System.out.println("TicTacToe - Use 1-9 to set a field!");
	        makeBoard();

	        MinMax current = computerPlayer;

	        for (;;) {
	            System.out.println("Player " + (playerTurn ? player : computer) + " is about to set");

	            if (playerTurn) {
	                final int[] indices = getInput();
	                board[indices[0]][indices[1]] = player.charAt(0);
	                int[] playerMove = current.minMaxChoose();
	            } else {
	                int[] computerMove = current.minMaxChoose();
	                if (computerMove != null) {
	                    board[computerMove[0]][computerMove[1]] = computer.charAt(0);
	                }
	            }

	            printBoard();

	            if (whoWon(player)) {
	                System.out.println("Player " + player + " won!\n");
	                break;
	            } else if (whoWon(computer)) {
	                System.out.println("Player " + computer + " (Computer) won!\n");
	                break;
	            } else if(boardFull()) {
	            	System.out.println("Tie");
	            	break;
	            }
	            

	            playerTurn = !playerTurn;
	        }

	        System.out.println("Do you want to play again? (y/n)");
	        String playAgain = sc.next();
	        if (!playAgain.equalsIgnoreCase("y")) {
	            System.out.println("Thanks for playing!");
	            return;
	        }

	        clearBoard();
	    }
	}


	private boolean boardFull() {
	    for (int i = 0; i < 3; i++) {
	        for (int j = 0; j < 3; j++) {
	            if (board[i][j] == (char)(i * 3 + j + '1')) {
	                return false;
	            }
	        }
	    }
	    return true;
	}
	
	private void makeBoard() {
	    for (int i = 0; i < 3; i++) {
	        for (int j = 0; j < 3; j++) {
	            board[i][j] = (char) (i * 3 + j + '1');
	        }
	    }
	    printBoard();
	}

	
	private void printBoard() {
		for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < 2) {
                System.out.println("---------");
            }
        }
        System.out.println();
    }
	
	private boolean whoWon(String player) {
		char playerWon = player.charAt(0);
	    for (int i = 0; i < 3; i++) {
	        if (board[i][0] == playerWon && board[i][1] == playerWon && board[i][2] == playerWon) {
	            return true; 
	        }
	        if (board[0][i] == playerWon && board[1][i] == playerWon && board[2][i] == playerWon) {
	            return true; 
	        }
	    }
	    if (board[0][0] == playerWon && board[1][1] == playerWon && board[2][2] == playerWon) {
	        return true; 
	    }
	    if (board[0][2] == playerWon && board[1][1] == playerWon && board[2][0] == playerWon) {
	        return true; 
	    }
	    return false;
	}

	private int[] getInput() {
	    int value;
	    for (;;) {
	        try {
	            value = Integer.parseInt(sc.next());
	            if (value >= 1 && value <= 9) {
	                return new int[]{(value - 1) / 3, (value - 1) % 3};
	            } else {
	                System.err.println(ERROR_MESSAGE);
	            }
	        } catch (final NumberFormatException e) {
	            System.err.println(ERROR_MESSAGE);
	        }
	    }
	}




	private static void clearBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j] = 0;
			}

		}
	}
	
}
