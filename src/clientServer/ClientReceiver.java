package clientServer;

import utilities.FileReader;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by Dickow on 24/02/2015.
 */

public class ClientReceiver implements Runnable, Compute {

    @Override
    public int executeTask() throws RemoteException {
        int sum = 0;
        ArrayList<Integer> temperatures;
        FileReader fileReader = new FileReader();
        temperatures = fileReader.readData("avgTemperatures.txt");
        if (temperatures.size() != 0) {
            for (int i = 0; i < temperatures.size(); i++) {
                sum += temperatures.get(i);
            }
            sum = sum / temperatures.size();
            return sum;
        } else {
            return 0;
        }

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
            Registry registry = LocateRegistry.createRegistry(1099);
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
