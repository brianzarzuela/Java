import java.util.Scanner;

public class TCPSocket{
    public TCPSocket(){};

    public void runAs(){
        boolean setup = false;
        Scanner scan = new Scanner(System.in);
        String client;
        System.out.print("\r\nWould you like to run as Client (A) or (B) : ");
        try{
            while(!setup){
                client = scan.next();
                if (client.equalsIgnoreCase("A")){
                    setup = true;
                    System.out.print("\r\nEnter Host: ");
                    String host = scan.next();
                    System.out.print("\r\nEnter Port: ");
                    String port = scan.next();

                    TCPClientSocket app = new TCPClientSocket(host, port);
                    app.info();
                    app.establishConnection();
                } else if(client.equalsIgnoreCase("B")){
                    setup = true;
                    TCPServerSocket app = new TCPServerSocket(null);
                    app.info();
                    app.establishConnection();
                } else{
                    System.out.println("\r\nUsage Error: Please enter (A) or (B) : ");
                }
            }
        } catch (Exception e ){
            e.printStackTrace();
        }
    }
}
