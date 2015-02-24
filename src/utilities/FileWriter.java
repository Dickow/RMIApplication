package utilities;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Dickow on 04/02/2015.
 */
public class FileWriter {

    /**
     * generate a random number between the given min and max
     *
     * @param min int
     * @param max int
     * @return int
     */
    public static int randomInt(int min, int max) {
        Random random = new Random();

        return random.nextInt((max - min) + 1) + min;

    }

    /**
     * Write random amount of numbers to a file
     * The numbers will be random between 14 and 24
     */
    public void writeDataToFile() {
        int count = 0;
        int random;
        Writer writer = null;

        try {
            random = randomInt(24, 30);
            writer = Files.newBufferedWriter(Paths.get("temperatures.txt"), StandardCharsets.UTF_8);
            while (count < random) {

                if (count == random - 1) {
                    writer.write(randomInt(14, 24) + "");
                } else {
                    writer.write(randomInt(14, 24) + ",");
                }
                count++;
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Write an integer ArrayList to a file.
     *
     * @param avgTemps ArrayList<Integer>
     */
    public void writeDataToFile(ArrayList<Integer> avgTemps, boolean newFile) {
        BufferedWriter writer = null;
        // if it is a new instance of the receiver we should write a new file instead of append
        if (!newFile) {
            try {
                java.io.FileWriter fileWriter = new java.io.FileWriter("avgTemperatures.txt", true);
                // writer = Files.newBufferedWriter(Paths.get("avgTemperatures.txt"), StandardCharsets.UTF_8);
                writer = new BufferedWriter(fileWriter);
            } catch (IOException e) {
                System.out.println(e);
            }

        } else { // if it is an existing receiver just append to the file
            try {
                java.io.FileWriter fileWriter = new java.io.FileWriter("avgTemperatures.txt", false);
                writer = new BufferedWriter(fileWriter);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        try {
            for (int i = 0; i < avgTemps.size(); i++) {
                if (i == avgTemps.size() - 1) {
                    writer.write(avgTemps.get(i) + "");
                    writer.newLine();
                } else {
                    writer.write(avgTemps.get(i) + ",");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
