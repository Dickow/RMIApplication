package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Dickow on 24/02/2015.
 */
public interface Compute extends Remote {

    <T> T executeTask(Task<T> tTask) throws RemoteException;

}
