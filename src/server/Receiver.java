package server;

/**
 * Created by Dickow on 04/02/2015.
 */

public class Receiver {

    public static void main(String[] args) {
        SensorReceiver sensorReceiver = new SensorReceiver();
        ClientReceiver clientReceiver = new ClientReceiver();

        sensorReceiver.run();
        clientReceiver.run();

    }
}


