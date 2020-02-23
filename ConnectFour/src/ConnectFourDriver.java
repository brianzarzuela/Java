public class ConnectFourDriver
{
    public static void main(String[] args)
    {
        ConnectFourPlayerInterface player1 = new HumanPlayer("Bob");
        ConnectFourPlayerInterface player2 = new HumanPlayer();
        ConnectFourPlayerInterface bot1 = new ComputerPlayer();
        ConnectFourPlayerInterface bot2 = new ComputerPlayer("Bobby");
        ConnectFourGameInterface game = new ConnectFourGame(player1, player2);
        game.playGame();
        game.getStats();
    }
}
