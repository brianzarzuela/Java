/**
 * <h1>
 *     BattleShip game play class for implementing TCP/IP connected BattleShip game
 * </h1>
 * <p>
 *     Directions: Run two separate instances of this class, each one being one of the players.
 *     One player should, when asked, declare themselves as Client A, the other Client B.
 * </p>
 * @author Brian Zarzuela
 */

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.*;
public class BattleShip {
    private char[][] myGrid=new char[10][10];
    private char[][] enemyGrid=new char[10][10];
    private String name; //player name
    private boolean[] hits=new boolean[6]; //keep track of the player's ships that have been hit
    private Point[] shipLocations=new Point[6]; //keeps track of the location of the player's ships
    private Socket socket=null;
    ServerSocket serverSocket=null;
    BufferedReader in=null;
    PrintWriter out=null;
    Scanner userInput=new Scanner(System.in);
    String opponentName, client;
    private int wins = 0, losses = 0, rounds = 0;

    public BattleShip(String name){
        this.name=name;
    }

    /**
     * initializes and resets the battleship gameboard
     */
    public void setGameboards(){
        for (int i=0; i<10; i++){
            for (int j=0; j<10; j++){
                myGrid[i][j]='.';
                enemyGrid[i][j]='.';
            }
        }
    }

    public String getName(){
        return name;
    }

    /**
     * prints the enemy grid to the console for the user to see
     */
    public void printEnemyGrid(){
        System.out.println("  0 1 2 3 4 5 6 7 8 9");
        for(int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                if(j == 0)
                    System.out.print(i + " " + enemyGrid[i][j]);
                else
                    System.out.print(" " + enemyGrid[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * prints the player grid to the console for the user to see
     */
    public void printMyGrid(){
        System.out.println("  0 1 2 3 4 5 6 7 8 9");
        for(int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                if(j == 0)
                    System.out.print(i + " " + myGrid[i][j]);
                else
                    System.out.print(" " + myGrid[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * sets the coordinates of ship 1, a 2x1 ship
     */
    public void placeShip1(){
        //check that the coordinates specified are valid
        //make sure ints are between 0 and 9 (ie exist on the game board)
        printMyGrid();
        int x, y;
        String orientation;
        Scanner userInput = new Scanner(System.in);
        boolean repeat = true;
        while (repeat)
            try {
                System.out.println("Enter the coordinates of ship 1's first point (a 2x1 ship) as X then Y");
                System.out.println("It is assumed you are entering the top-most, leftmost point of the ship.");
                x = userInput.nextInt();
                y = userInput.nextInt();
                System.out.println("enter the orientation of the ship: enter 'h' for horizontal and 'v' for vertical");
                orientation=userInput.next();
                if (orientation.equalsIgnoreCase("h")){
                    if (areCoordsValid(x, y, x+1, x+1)){
                        shipLocations[0]=new Point(x, y);
                        shipLocations[1]=new Point(x+1, y);
                        repeat=false;
                    }
                    else{
                        System.out.println("invalid coordinates, please try again.");
                    }
                }
                else if (orientation.equalsIgnoreCase("v")){
                    if (areCoordsValid(x, y, y+1, y+1)){
                        shipLocations[0]=new Point(x,y);
                        shipLocations[1]=new Point(x, y+1);
                        repeat=false;
                    }
                    else {
                        System.out.println("invalid coordinates, please try again.");
                    }
                }
                else{
                    System.out.println("wrong input, please try again.");
                }
            } catch (Exception e) {
                userInput.next();
                System.out.println("enter valid coordinates and orientation for ship 1");
            }
    }
    /**
     * sets the coordinates of ship 2, a 4x1 ship, and places a 4x1 ship between the coordinates specified
     */
    public void placeShip2(){
        printMyGrid();
        int x, y;
        String orientation;
        Scanner userInput = new Scanner(System.in);
        boolean repeat = true;
        while (repeat)
            try {
                System.out.println("Enter the coordinates of ship 2's first point (a 4x1 ship) as X then Y ");
                System.out.println("It is assumed you are entering the top-most, leftmost point of the ship.");
                x = userInput.nextInt();
                y = userInput.nextInt();
                System.out.println("enter the orientation of the ship: enter 'h' for horizontal and 'v' for vertical");
                orientation=userInput.next();
                if (orientation.equalsIgnoreCase("h")){
                    if (areCoordsValid(x, y, x+3, x+3)){
                        shipLocations[2]=new Point(x, y);
                        shipLocations[3]=new Point(x+1, y);
                        shipLocations[4]=new Point(x+2, y);
                        shipLocations[5]=new Point(x+3, y);
                        //make sure ships dont overlap
                        for (int i=2; i<=5; i++){
                            if (shipLocations[i].equals(shipLocations[0])|| shipLocations[i].equals(shipLocations[1])){
                                repeat=true;
                                System.out.println("ships overlap please try again");
                                break;
                            }
                            else {
                                repeat=false;}
                        }

                    }
                    else{
                        System.out.println("invalid coordinates, please try again.");
                    }
                }
                else if (orientation.equalsIgnoreCase("v")){
                    if (areCoordsValid(x, y, y+3, y+3)){
                        shipLocations[2]=new Point(x, y);
                        shipLocations[3]=new Point(x, y+1);
                        shipLocations[4]=new Point(x, y+2);
                        shipLocations[5]=new Point(x, y+3);
                        for (int i=2; i<=5; i++){
                            if (shipLocations[i].equals(shipLocations[0])|| shipLocations[i].equals(shipLocations[1])){
                                repeat=true;
                                System.out.println("ships overlap please try again");
                                break;
                            }
                            else {
                                repeat=false;}
                        }
                    }
                    else {
                        System.out.println("invalid coordinates, please try again.");
                    }
                }
                else{
                    System.out.println("wrong input, please try again.");
                }
            } catch (Exception e) {
                userInput.next();
                System.out.println("enter valid coordinates and orientation for ship 1");
            }
    }

    /**
     * checks to see if a given 4 coordinates exist on the gameboard
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return true if all passed coords are between 0 and 9
     */
    public boolean areCoordsValid(int x1, int y1, int x2, int y2){
        //make sure ints are between 0 and 9 (ie exist on the gameboard
        if (x1>9 || x1<0){
            return false;
        }
        if (y1 > 9 || y1< 0){
            return false;
        }
        if(x2>9||x2<0){
            return false;
        }
        if (y2>9||y2<0){
            return false;
        }
        return true;
    }

    public boolean areCoordsValid(int x, int y){
        return( x<= 9 && x >=0 && y <= 9 && y >= 0 );
    }

    /**
     *checks to see if the opponents guess hit a ship
     * @param bomb
     * @return 0 for no hit, 1 for hit
     */
    public int checkForHit(Point bomb){ //if the point guessed is a ship
        for (int i=0; i<6; i++){
            if (bomb.equals(shipLocations[i])){
                this.hits[i]=true;
                System.out.println("It's a hit!");
                return 1;
            }
        }
        System.out.println("Enemy missed!");
        return 0;
    }

    /**
     * checks to see if all ship locations have been guessed. returns true if so, and ends game with a loss for the player
     * @return
     */
    public boolean checkForLoss(){
        for (boolean bool : this.hits){
            if (!bool)
                return false;
        }
        return true;
    }


    /**get user input for guessing enemys ship location and return a string that can be sent to the opponent as a guess
     *
     * @return
     */
    public String makeGuess(){
        int x = -1, y = -1;
        String values;
        Scanner userInput = new Scanner(System.in);
        while (!areCoordsValid(x, y)){
            try {
                System.out.println("enter the row number");
                x = userInput.nextInt();
                System.out.println("enter the column number");
                y = userInput.nextInt();
            }
            catch(Exception e){
                x = -1;
                y = -1;
                System.out.println("try to sink the enemy ship by guessing integers between 0 and 9.");
                userInput.next();
            }
        }
        values = x + "," + y;
        return values;
    }

    /**
     * records the results of the guess on the player's record of the enemy's game board
     * @param guess
     * @param hit
     * @return
     */
    public boolean markEnemyBoard(String guess, int hit){
        String[] guessArray = guess.split(",");
        if (hit==1){
            enemyGrid[Integer.parseInt(guessArray[0])][Integer.parseInt(guessArray[1])]='X';
            return true;
        }
        if (hit==0){
            enemyGrid[Integer.parseInt(guessArray[0])][Integer.parseInt(guessArray[1])]='X';
            return true;
        }
        return false;
    }

    /**
     * tries to connect to the server as a socket. if it can't connect, it assumes the server needs to be created and creates a server socket
     */
    public void makeConnection(){
        String ip;
        int port;
        boolean connected = false;
        System.out.println("\r\nWould you like to connect as Client (A) - Client or Client (B) - Server : ");
        client = userInput.next();
        try{
            while(!connected){
                if(client.equalsIgnoreCase("A")){

                }
                else if (client.equalsIgnoreCase("B")){

                }
                else {
                    System.out.println("Usage Error - Enter (A) or (B): ");
                }
            }
        } catch (Exception e){
            System.out.println("Fatal connection error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void makeConnection_v0(){
        String client, ip;
        int port;
        boolean connected = false;
        System.out.print("\r\nWould you like to connect as Client (A) or Client (B) : ");
        client = userInput.next();
        try{
            while(!connected){
                if (client.equalsIgnoreCase("A")) {
                    System.out.print("\r\nEnter your friend's ip address : ");
                    ip = userInput.next();
                    System.out.print("\r\nEnter the port number : ");
                    port = userInput.nextInt();
                    socket = new Socket(ip, port);
                    System.out.println("Connection Established: Host=" + socket.getInetAddress());
                    in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    out=new PrintWriter(new DataOutputStream(socket.getOutputStream()));
                    connected = true;
                } else
                if (client.equalsIgnoreCase("B")){
                    System.out.print("\r\nEnter the port number : ");
                    port = userInput.nextInt();
                    this.serverSocket = new ServerSocket(port, 1, InetAddress.getLocalHost());
                    System.out.println("Server Created: Host=" + serverSocket.getInetAddress()
                            + " Port=" + serverSocket.getLocalPort());
                    Socket connectedSocket=this.serverSocket.accept();
                    System.out.println("other player connected");
                    in=new BufferedReader(new InputStreamReader(connectedSocket.getInputStream()));
                    out=new PrintWriter(connectedSocket.getOutputStream());
                    connected = true;
                }
                else{
                    System.out.println("Usage Error - Enter (A) or (B) : ");
                }
            }
        } catch (Exception e){
            System.out.println("no server socket :(");
            e.printStackTrace();
        }

    }

    /**
     * the logic for playing the game
     */
    public void playGame(){
        String input;
        do{
            setGameboards();
            setShipLocations();
            makeConnection();
            singleRound();
            System.out.print("\r\nPlay again (Y)es / (N)o : ");
            input = userInput.next();
            printStatistics();
        } while(input.equalsIgnoreCase("Y"));

    }

    private void printStatistics(){
        System.out.printf("\r\nRounds played: %d\r\nWins   : %d\r\nLosses : %d\r\n", rounds, wins, losses);
    }

    private void singleRound(){
        try {
            out.println(getName());
            out.flush();
            opponentName = in.readLine();
            System.out.println(opponentName);

            if (client.equalsIgnoreCase("B")) { //we are the server and we go first
                printEnemyGrid();
                String guess = makeGuess();
                out.println(guess);
                out.flush();
                String feedback=in.readLine();
                markEnemyBoard(guess, Integer.parseInt(feedback));
            }

            int hit;
            String feedback;
            String bomb;
            boolean playGame=true;
            while (playGame) {
                System.out.println("in game while loop");
                bomb=in.readLine();
                System.out.println(bomb);
                if (bomb != null) {
                    System.out.println("guess received: " + bomb);
                    String[] bombArray = bomb.split(",");
                    Point guessed = new Point(Integer.parseInt(bombArray[0]), Integer.parseInt(bombArray[1]));
                    System.out.println("processing enemy attack");
                    hit = checkForHit(guessed); //check if the coordinates match any saved ship locations
                    //send feedback to opponent on hit/miss
                    if (checkForLoss()) { //check if all ships have been sunk
                        out.println("-1,-1");
                        out.flush();
                        System.out.println("you have lost!");
                        losses++;
                        rounds++;
                        break;
                    } else {
                        out.println(hit);
                        out.flush();
                    }
                }

                //guess a space
                printEnemyGrid();
                String guess=makeGuess();
                out.println(guess);
                out.flush();
                feedback=in.readLine();
                if (feedback.equalsIgnoreCase("-1,-1")) {
                    System.out.println("You have won!");
                    wins++;
                    rounds++;
                    break;
                }
                else{
                    markEnemyBoard(guess, Integer.parseInt(feedback));
                }
            }
        }
        catch(java.io.IOException e){
            e.printStackTrace();
        }
    }


    private void setShipLocations(){
        placeShip1();
        placeShip2();
    }
}