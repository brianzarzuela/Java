/**
 * <h1>Human Player for Connect Four Game</h1>
 * This is a human player class implementing ConnectFourPlayerInterface to be used in ConnectFourGame.
 * A human player is comprised of the following attributes: name, number of games won, player number, and game piece.
 *
 * @author Brian Zarzuela
 * @version 1.1 2019-02-07
 * @since 2019-02-04
 */

import java.util.Scanner;

class HumanPlayer implements ConnectFourPlayerInterface {

    private String name;
    private int numberOfWins, playerNumber;
    private char gamePiece;

    /**
     * Constructor for class HumanPlayer.
     * By default the player's name is Group 18.
     */
    public HumanPlayer() {
        this.name = "Group 18";
        this.numberOfWins = 0;
    }

    /**
     * Constructor for class HumanPlayer.
     * @param playerName User entered name of the player.
     */
    public HumanPlayer(String playerName) {
        this.name = playerName;
        this.numberOfWins = 0;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getNumberOfWins() {
        return this.numberOfWins;
    }
    
    public void addWin() {
        this.numberOfWins += 1;
    }
    
    public char getGamePiece() {
        return this.gamePiece;
    }
    
    public void setGamePiece(char gamePiece) {
        this.gamePiece = gamePiece;
    }
    
    public void setPlayerNumber(int num) {
        this.playerNumber = num;
    }

    /**
     * <b>Simulates a player's turn.</b>
     * Asks the player to enter a column number.
     * @return 0-index based integer representing the number column the player has chosen.
     */
    public int takeTurn() {
        System.out.printf("Player %d: %s select a column \n", this.playerNumber, this.name);
        Scanner reader = new Scanner(System.in);
        return reader.nextInt();
    }

}