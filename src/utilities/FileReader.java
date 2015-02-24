package utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Dickow on 04/02/2015.
 */
public class FileReader {

    public ArrayList<Integer> readData() {
        ArrayList<Integer> array = new ArrayList<Integer>();
        try {
            BufferedReader reader = new BufferedReader(new java.io.FileReader("temperatures.txt"));
            String str;

            str = reader.readLine();

            String[] arg = str.split(",");
            for (int i = 0; i < arg.length; i++) {
                array.add(Integer.parseInt(arg[i]));
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
