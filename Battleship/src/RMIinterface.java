import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIinterface extends Remote {
    void doSomething() throws RemoteException;
}
