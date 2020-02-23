import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class TCPClientSocket {
    private Socket socket;

    public TCPClientSocket(String  IP, String PORT) throws Exception{
        socket = new Socket(InetAddress.getByName(IP), Integer.parseInt(PORT));
    }

    public void info(){
        System.out.println("\r\nConnected to Server: " + socket.getInetAddress());
    }

    public void establishConnection(){
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream, true);
            InputStream inputStream = socket.getInputStream();
            BufferedReader received = new BufferedReader(new InputStreamReader(inputStream));

            String receiveMessage, sendMessage;
            for(;;){
                sendMessage = reader.readLine();
                writer.println("<Client> " + sendMessage);
                writer.flush();

                if ( sendMessage.contains("bye")){
                    System.out.println("'bye' sent");
                    break;
                }

                if ( (receiveMessage = received.readLine()) != null )
                    if (receiveMessage.contains("bye"))
                    {
                        System.out.println("'bye' received");
                        break;
                    }
                    else
                        System.out.println(receiveMessage);
            }

            System.out.println("Communication ended...");

        } catch (Exception e){
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) throws Exception{
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter Host: ");
//        String host = scanner.next();
//        System.out.println("Enter Port: ");
//        String port = scanner.next();
//
//        TCPClientSocket app = new TCPClientSocket(host, port);
//        app.info();
//        app.establishConnection();
//    }
}
