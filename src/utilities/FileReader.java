package utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Dickow on 04/02/2015.
 */
public class FileReader {

    /**
     * reads every line in the supplied file.
     * Reads comma separated valus and adds them to the
     * ArrayList
     *
     * @param fileName String
     * @return ArrayList
     */
    public ArrayList<Integer> readData(String fileName) {
        ArrayList<Integer> array = new ArrayList<Integer>();
        try {
            BufferedReader reader = new BufferedReader(new java.io.FileReader(fileName));
            String str;

            while ((str = reader.readLine()) != null) {

                String[] arg = str.split(",");
                for (int i = 0; i < arg.length; i++) {
                    array.add(Integer.parseInt(arg[i]));
                }
            }
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return array;
    }


}
