package minMax;

public class MinMax implements MinMaxInterface{
    private char[][] board;
    private char player;
    private char computer;
    int count = 1;

    public MinMax(char[][] board, char player, char computer) {
        this.board = board;
        this.player = player;
        this.computer = computer;
    }

    @Override
    public int[] minMaxChoose() {
        int[] bestMove = null;
        int bestScore = Integer.MIN_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != player && board[i][j] != computer) {
                    char originalValue = board[i][j];
                    board[i][j] = computer;
                    int score = minimax(board, 0, false);
                    board[i][j] = originalValue;
//                    System.out.println("Score: " + score + " :" + count++);
//                    System.out.println("Best Score: " + bestScore);
//                    System.out.println("Best Move: " + Arrays.toString(bestMove));
//                    System.out.println(i + " " + j);
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = new int[]{i, j};
                    }
                }
            }
        }

        return bestMove;
    }
    
	private int minimax(char[][] board, int depth, boolean isMaximizing) {
        if (isGameOver(board)) {
            if (whoWon(board, computer)) {
                return 1;
            } else if (whoWon(board, player)) {
                return -1;
            } else {
                return 0;
            }
        }

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] != player && board[i][j] != computer) {
                        board[i][j] = player;
                        int score = minimax(board, depth + 1, false);
                        board[i][j] = (char) (i * 3 + j + '1'); 
                        bestScore = Math.max(score, bestScore);
              
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] != player && board[i][j] != computer) {
                        board[i][j] = computer;
                        int score = minimax(board, depth + 1, true);
                        board[i][j] = (char) (i * 3 + j + '1');
                        bestScore = Math.min(score, bestScore);
                        
                    }
                }
            }
            return bestScore;
        }
    }


	private boolean whoWon(char[][] board, char computer) {
		
		for (int i = 0; i < 3; i++) {
	        if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
	            return true; 
	        }
	        if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
	            return true; 
	        }
	    }

	    if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
	        return true; 
	    }

	    if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
	        return true; 
	    }

	    return false;
	}

	private boolean isGameOver(char[][] board) {
		
		for (int i = 0; i < 3; i++) {
	        for (int j = 0; j < 3; j++) {
	            if (board[i][j] != player && board[i][j] != computer) {
	                return false; 
	            }
	        }
	    }
	    
	    return true;
	}
}



