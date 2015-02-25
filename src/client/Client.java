package client;

import clientServer.Compute;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by Dickow on 24/02/2015.
 */
public class Client {
    public static void main(String[] args) {
        try {
            String name = "ClientReceiver";
            Registry registry = LocateRegistry.getRegistry(1099);
            Compute compute = (Compute) registry.lookup(name);
            int avgTemp = compute.executeTask();
            System.out.println("avgTemp = " + avgTemp);

        } catch (Exception e) {
            System.out.println("error in rmi client occured");
            e.printStackTrace();
        }
    }
}
