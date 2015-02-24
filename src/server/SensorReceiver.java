package server;

import utilities.FileWriter;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.ArrayList;

/**
 * Created by Dickow on 24/02/2015.
 */
public class SensorReceiver implements Runnable {


    /**
     * convert a byte array to an integer array
     * and return it
     *
     * @param bytes
     * @return int[]
     */
    public static int[] byteToInt(byte[] bytes) {
        int[] returnArray;
        IntBuffer intBuffer1 = ByteBuffer.wrap(bytes).order(ByteOrder.BIG_ENDIAN).asIntBuffer();
        returnArray = new int[intBuffer1.remaining()];
        intBuffer1.get(returnArray);
        return returnArray;
    }

    @Override
    public void run() {

        // setup the declarations
        ServerSocket serverSocket = null;
        DataInputStream dataInputStream = null;
        Socket serviceSocket = null;
        int avgTemp, temp = 0;
        FileWriter fileWriter = new FileWriter();
        boolean done = false;
        boolean newInstance = true;

        // open then server socket for the input stream
        try {
            serverSocket = new ServerSocket(9999);
        } catch (IOException e) {
            System.out.println("couldn't open a server socket on port 9999");
        }
        while (!done) {

            // setup the input stream
            try {
                serviceSocket = serverSocket.accept();
                dataInputStream = new DataInputStream(serviceSocket.getInputStream());
            } catch (IOException e) {
                System.out.println("The dataInputStream could not be created");
            }

        /*create buffer to hold all the bytes
          put all the bytes into the buffer
          parse the bytes to int and print */
            try {
                ArrayList<Integer> avgTemperatures = new ArrayList<Integer>();
                byte[] bytes = new byte[1024];
                dataInputStream.read(bytes);
                int[] array = byteToInt(bytes);

                for (int i = 0; i < array.length; i++) {

                    if (array[i] == 0) {
                        break;
                    } else {
                        temp += array[i];
                        avgTemp = temp / (i + 1);
                        System.out.println("average temperature: " + avgTemp);
                        avgTemperatures.add(avgTemp);
                    }
                }
                if (newInstance) {
                    fileWriter.writeDataToFile(avgTemperatures, true); // write the data to the avg temp File
                    newInstance = false;
                } else {
                    fileWriter.writeDataToFile(avgTemperatures, false);
                }
                temp = 0; // reset the temperature
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // close all resources when done
        try {
            dataInputStream.close();
            serviceSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}




