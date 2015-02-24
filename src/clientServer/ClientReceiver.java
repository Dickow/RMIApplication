package clientServer;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Dickow on 24/02/2015.
 */

public class ClientReceiver implements Runnable, Compute {

    @Override
    public int executeTask() throws RemoteException {
        return 20;
    }


    @Override
    public void run() {

//        if (System.getSecurityManager() == null) {
//            System.setSecurityManager(new SecurityManager());
//        }
        try {
            String name = "ClientReceiver";
            Compute receiver = new ClientReceiver();
            Compute stub = (Compute) UnicastRemoteObject.exportObject(receiver, 0);
            System.out.println("stub setup");
            Registry registry = LocateRegistry.getRegistry();
            System.out.println("registry found");
            registry.rebind(name, stub);
            System.out.println("registry rebound");
            System.out.println("Compute engine bound");

        } catch (Exception e) {
            System.out.println("Compute engine failed to setup");
            e.printStackTrace();
        }

    }


}
