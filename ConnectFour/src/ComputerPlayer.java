/**
 * <h1>Computer Player for Connect Four Game</h1>
 * This is a computer player class extending the HumanPlayer class to be used in ConnectFourGame.
 * A computer player is AI human player that utilizes algorithms [non-trivial] in choosing a column.
 *
 * @author Brian Zarzuela
 * @version 0.1
 * @since 2019-02-07
 */

class ComputerPlayer extends HumanPlayer{

    private int depth = 2;
    private int nextMoveLocation = -1;

    /**
     * Constructor for class Computer Player.
     * Invokes the constructor of the superclass, HumanPlayer()
     */
    public ComputerPlayer(char[]][] board, ConnectFourPlayerInterface player) {
        super();
        this.board = board;
        this.oponentPiece = piece;
    }

    /**
     * Constructor for class Computer Player.
     * Invokes the constructor of the superclass, HumanPlayer(playerName)
     * @param playerName User entered name of the player.
     */
    public ComputerPlayer(char[]][] board, ConnectFourPlayerInterface player, String playerName) {
        super(playerName);
    }

    /**
     * Overriding takeTurn() method of HumanPlayer to allow AI to decide on what move to make.
     * @return 0-index based integer representing the number column the computer has chosen.
     */
    @Override
    public int takeTurn() {
        return 0;
    }
}
