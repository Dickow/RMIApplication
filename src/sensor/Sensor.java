package sensor;


/**
 * Created by Dickow on 04/02/2015.
 */

import utilities.FileReader;
import utilities.FileWriter;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

public class Sensor {
    public static void main(String[] args) {
        // create a data generator
        FileWriter fileWriter = new FileWriter();

        // setup the socket and output stream
        Socket sensorSocket = null;
        DataOutputStream outputStream = null;

        // the byte array to hold the information
        byte[] bytes;

        // write some random data to a file that we can read
        fileWriter.writeDataToFile();

        // int array for the temperatures read from the file
        int[] temperatures = getData();

        // setup the connection to the server
        try {
            sensorSocket = new Socket("localhost", 9999);
            outputStream = new DataOutputStream(sensorSocket.getOutputStream());
        } catch (IOException e) {
            System.out.println("could not connect to the server ");
        }

        if (sensorSocket != null && outputStream != null) {
            // allocate space for the byte array
            // and convert the integer array to a byte array
            ByteBuffer byteBuffer = ByteBuffer.allocate(temperatures.length * 4);
            IntBuffer intBuffer = byteBuffer.asIntBuffer();
            intBuffer.put(temperatures);

            // put the integers into the byte array
            bytes = byteBuffer.array();

            // write the byte array to the server
            try {
                outputStream.write(bytes, 0, bytes.length);
            } catch (IOException e) {
                System.out.println("could not write to the server");
            }

        }

        // close all resources when done
        try {
            outputStream.close();
            sensorSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int[] getData() {
        // create a data reader
        FileReader fileReader = new FileReader();
        ArrayList<Integer> arrayList;
        int[] intArray;
        arrayList = fileReader.readData("temperatures.txt");

        intArray = new int[arrayList.size()];

        for (int i = 0; i < arrayList.size(); i++) {
            intArray[i] = arrayList.get(i);
        }

        return intArray;
    }
}
