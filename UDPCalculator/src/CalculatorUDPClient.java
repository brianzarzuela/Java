import java.net.*;
import java.io.*;
import java.util.Scanner;

/**
 * <p>
 *     Client class for communicating with server, CalculatorUDPServer, using UDP
 * </p>
 * @author Brian Zarzuela
 * @version %I%, %G%
 * @since 1.0
 */

public class CalculatorUDPClient {
    private DatagramSocket socket;
    private InetAddress ip;
    private int port;

    private CalculatorUDPClient(int port) throws IOException{
        this.port = port;
        socket = new DatagramSocket();
        ip = InetAddress.getLocalHost();
    }

    private void run() throws IOException{
        for(;;){
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter equation <num> <operation> <num>: ");
            String input = scan.nextLine() + " ";

            byte[] buf = input.getBytes();
            DatagramPacket send = new DatagramPacket(buf, buf.length, ip, port);
            socket.send(send);

            if (input.contains("bye"))
                break;

            buf = new byte[5000];
            DatagramPacket receive = new DatagramPacket(buf, buf.length);
            socket.receive(receive);
            String received = new String(buf, 0, buf.length);

            if(received.contains("Error"))
                System.out.println(received);
            else
                System.out.println("Answer = " + received);
        }
    }

    public static void main(String[] args){
        try{
            new CalculatorUDPClient(123).run();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
