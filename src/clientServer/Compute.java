package clientServer;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Dickow on 24/02/2015.
 */
public interface Compute extends Remote {

    int executeTask() throws RemoteException;

}
