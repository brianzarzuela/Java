/**
 *  <h1>Connect Four Game</h1>
 *  The ConnectFourGame class implements two connect four interfaces to create a connect four game.
 *  The game consists of two people who alternately drop their unique game pieces down a column of the game board.
 *  Once a player has 4 pieces in a row either horizontally, vertically, or diagonally, then they win the game.
 *
 * @author Brian Zarzuela
 * @verion 1.3, 2019-02-07
 * @since 2019-02-04
 */

import java.util.Scanner;

class ConnectFourGame implements ConnectFourGameInterface {

    private ConnectFourPlayerInterface player1;
    private ConnectFourPlayerInterface player2;
    private Scanner reader;
    /**
     * This implementation of a Connect Four game will be played on a board with 6 rows and 7 columns.
     */
    private char[][] board = new char[6][7];
    private boolean gameInProgress;
    /**
     * The attribute, result, stores the result of the game based on the following key:
     * 0 : tie
     * 1 : player 1 wins
     * 2 : player 2 wins
     */
    private int result;
    /**
     * The attribute, turns, tracks how many turns have taken place in the game, used to identify a tie.
     */
    private int turns;
    /**
     * The attribute, defaultPiece, holds the default character for empty cells in the board array.
     */
    private char defaultPiece = '-';

    /**
     * Constructor for class ConnectFourGame.
     * @param player1 Any ConnectFourPlayerInterface player, human or computer
     * @param player2 Any ConnectFourPlayerInterface player, human or computer
     */
    public ConnectFourGame(ConnectFourPlayerInterface player1, ConnectFourPlayerInterface player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.reader = new Scanner(System.in);
    }

    public void getStats() {
        System.out.printf("%s (Player 1) has %d wins and %s (Player 2) has %d wins.\n",
                this.player1.getName(), this.player1.getNumberOfWins(),
                this.player2.getName(), this.player2.getNumberOfWins());
    }

    /**
     * Sets all char elements in the board array equal to the defaultPiece.
     */
    private void clearBoard() {
        for (int i = 0; i < this.board.length; i++)
            for (int j = 0; j < this.board[i].length; j++)
                this.board[i][j] = defaultPiece;
    }

    private void setGamePieces() {
        System.out.printf("%s select a single char game piece \n", this.player1.getName());
        Scanner reader = new Scanner(System.in);
        this.player1.setGamePiece(reader.next().charAt(0));
        this.player1.setPlayerNumber(1);
        System.out.printf("%s select a single char game piece \n", this.player2.getName());
        this.player2.setGamePiece(reader.next().charAt(0));
        this.player2.setPlayerNumber(2);
    }

    /**
     * Prints out all elements of the board array formatted with numbers 0-6 underneath denoting the column numbers.
     */
    private void displayBoard() {
        System.out.println();
        for (char[] row : this.board) {
            for (char col : row)
                System.out.print(col + "\t");
            System.out.println("\n");
        }
        System.out.println("0\t1\t2\t3\t4\t5\t6");
        System.out.println();
    }

    /**
     * Drops players piece into the appropriate column
     * @param column The column the player chose
     * @param piece The current player's piece
     */
    private void placePiece(int column, char piece) {
        for (int r = this.board.length - 1; r >= 0; r--) {
            if (this.board[r][column] == defaultPiece) {
                this.board[r][column] = piece;
                return;
            }
        }
    }

    /**
     * Uses a for loop to check through the elements of the board array for any rows of four.
     * Also checks to see if the maximum number of turns have been reached.
     */
    private void checkBoard() {
        for (int r = 5; r >= 0; r--) {
            for (int c = 0; c <= 6; c++) {
                if (c <= 3) {
                    /**
                     * <b>Check for any horizontal wins.</b>
                     * Only check up through column 4 and look ahead to the next 3 columns for efficiency.
                     */
                    if (this.board[r][c] == this.board[r][c+1] &&
                            this.board[r][c] == this.board[r][c+2] &&
                            this.board[r][c] == this.board[r][c+3]) {
                        if (this.board[r][c] == this.player1.getGamePiece()) {
                            result = 1;
                            gameInProgress = false;
                            return;
                        }
                        else if (this.board[r][c] == this.player2.getGamePiece()) {
                            result = 2;
                            gameInProgress = false;
                            return;
                        }
                    }
                }
                if (r >= 3) {
                    /**
                     * <b>Check for any vertical wins.</b>
                     * Only check through the bottom 3 rows and look ahead to the next 3 rows for efficiency.
                     */
                    if (this.board[r][c] == this.board[r-1][c] &&
                            this.board[r][c] == this.board[r-2][c] &&
                            this.board[r][c] == this.board[r-3][c]) {
                        if (this.board[r][c] == this.player1.getGamePiece()) {
                            result = 1;
                            gameInProgress = false;
                            return;
                        }
                        else if (this.board[r][c] == this.player2.getGamePiece()) {
                            result = 2;
                            gameInProgress = false;
                            return;
                        }
                    }
                    /**
                     * <b>Check for any forward diagonal wins.</b>
                     * Checks through the bottom 3 rows and first 4 columns and
                     * looks ahead to the next 3 positions in an up-right diagonal.
                     */
                    if (c <=3) {
                        if (this.board[r][c] == this.board[r-1][c+1] &&
                                this.board[r][c] == this.board[r-2][c+2] &&
                                this.board[r][c] == this.board[r-3][c+3]) {
                            if (this.board[r][c] == this.player1.getGamePiece()) {
                                result = 1;
                                gameInProgress = false;
                                return;
                            }
                            else if (this.board[r][c] == this.player2.getGamePiece()) {
                                result = 2;
                                gameInProgress = false;
                                return;
                            }
                        }
                    }
                    /**
                     * <b>Check for any backward diagonal wins.</b>
                     * Checks through the bottom 3 rows and last 4 columns and
                     * looks ahead to the next 3 positions in an up-left diagonal.
                     */
                    else {
                        if (this.board[r][c] == this.board[r-1][c-1] &&
                                this.board[r][c] == this.board[r-2][c-2] &&
                                this.board[r][c] == this.board[r-3][c-3]) {
                            if (this.board[r][c] == this.player1.getGamePiece()) {
                                result = 1;
                                gameInProgress = false;
                                return;
                            }
                            else if (this.board[r][c] == this.player2.getGamePiece()) {
                                result = 2;
                                gameInProgress = false;
                                return;
                            }
                        }
                    }
                }
            }
        }
        /**
         * <b>Check for any ties.</b>
         * Ties occur when there are no more moves left on the board and neither player connected four.
         * In a 6 by 7 game, a tie occurs after 42 moves with no winner.
         */
        if (this.turns == 42) {
            gameInProgress = false;
            return;
        }
    }

    /**
     * <b>Simulates a game of Connect Four</b>
     * Checks for a winner after each player takes their turn. If a winner is found, or if the game ends in a tie,
     * the result of the game is printed and the current round ends. The game continues so long as the user enters
     * [Y]es after the end of every round.
     */
    public void playGame() {
        char playAgain;
        int column;
        setGamePieces();
        System.out.println("Welcome to Connect Four! \n");
        do {
            this.gameInProgress = true;
            this.result = 0;
            this.turns = 0;
            this.clearBoard();
            this.displayBoard();
            while(this.gameInProgress) {
                column = this.player1.takeTurn();
                this.turns++;
                this.placePiece(column, this.player1.getGamePiece());
                this.displayBoard();
                this.checkBoard();
                if (!this.gameInProgress)
                    break;
                column = this.player2.takeTurn();
                this.turns++;
                this.placePiece(column, this.player2.getGamePiece());
                this.displayBoard();
                this.checkBoard();
                if (!this.gameInProgress)
                    break;
            }
            switch (this.result) {
                case 1 :
                    System.out.printf("%s has won the game \n", this.player1.getName());
                    this.player1.addWin();
                    break;
                case 2 :
                    System.out.printf("%s has won the game \n", this.player2.getName());
                    this.player2.addWin();
                    break;
                default :
                    System.out.println("You tied");
                    break;
            }
            System.out.println("Would you like to play again? y/n");
            playAgain = this.reader.next().charAt(0);
        } while (playAgain == 'Y' || playAgain == 'y');
    }
}
