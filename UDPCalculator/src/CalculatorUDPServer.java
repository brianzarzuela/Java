import java.net.*;
import java.io.*;
import java.util.StringTokenizer;

/**
 * <p>
 *     Server class for communicating with server, CalculatorUDPServer, using UDP
 *     Implements usage of Calculator class
 * </p>
 * @author Brian Zarzuela
 * @version 1.0, 10 April 2019
 */

public class CalculatorUDPServer implements CalculatorInterface{
    private DatagramSocket socket;
    private int port;

    private CalculatorUDPServer(int port) throws IOException{
        this.port = port;
        socket = new DatagramSocket(this.port);
        System.out.println("Listening on port: " + socket.getLocalPort());
    }

    public int performOperation(OperationalFunctionalInterface ofi, int num1, int num2){
        return ofi.operation(num1, num2);
    }

    private void run() throws IOException{
        for(;;){
            byte[] buf = new byte[5000];
            DatagramPacket receive = new DatagramPacket(buf, buf.length);
            socket.receive(receive);

            String received = new String(buf, 0, buf.length);
            received = received.trim();
            System.out.println("Received: " + received);

            if(received.contains("bye")){
                System.out.println("EXITING...");
                break;
            }

            StringTokenizer st = new StringTokenizer(received);

            String sent = new Calculator(st).executable();

            buf = sent.getBytes();
            port = receive.getPort();

            DatagramPacket send = new DatagramPacket(buf, buf.length, InetAddress.getLocalHost(), port);
            socket.send(send);
        }
    }

    public static void main(String[] args){
        try{
            new CalculatorUDPServer(123).run();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
