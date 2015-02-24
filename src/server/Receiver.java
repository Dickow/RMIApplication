package server;

/**
 * Created by Dickow on 04/02/2015.
 */

import clientServer.ClientReceiver;

public class Receiver {

    public static void main(String[] args) {

        SensorReceiver sensorReceiver = new SensorReceiver();
        ClientReceiver clientReceiver = new ClientReceiver();
        new Thread(sensorReceiver).start();
        new Thread(clientReceiver).start();


    }
}


