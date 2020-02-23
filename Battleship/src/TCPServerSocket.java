import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServerSocket {
    private ServerSocket server;

    public TCPServerSocket(String IP) throws Exception{
        if (IP != null && !IP.isEmpty())
            server = new ServerSocket(0,1, InetAddress.getByName(IP));
        else
            server = new ServerSocket(0,1, InetAddress.getLocalHost());
    }

    public void info(){
        System.out.println("\r\nRunning Server: Host=" + server.getInetAddress() +
                            " Port=" + server.getLocalPort());
    }

    public void establishConnection(){
        try{
            Socket socket = server.accept();

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream, true);
            InputStream inputStream  = socket.getInputStream();
            BufferedReader received = new BufferedReader(new InputStreamReader(inputStream));

            String receiveMessage, sendMessage;
            for(;;){
                if ( (receiveMessage = received.readLine()) != null )
                    if (receiveMessage.contains("bye")){
                        System.out.println("'bye' received");
                        break;
                    }
                    else
                        System.out.println(receiveMessage);

                sendMessage = reader.readLine();
                writer.println("<Server> " + sendMessage);
                writer.flush();

                if ( sendMessage.contains("bye")){
                    System.out.println("'bye' sent");
                    break;
                }
            }

            System.out.println("Communication ended...");

        } catch (Exception e){
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) throws Exception{
//        TCPServerSocket app = new TCPServerSocket(null);
//        app.info();
//        app.establishConnection();
//    }
}
