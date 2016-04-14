package mummyding.github;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mummyding on 16-4-4.
 */
public class FileUtil {

    public static String readFromFile(String fileName) {
        File file = new File(fileName);
        StringBuilder fileContent = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                fileContent.append(tempString+" ");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return fileContent.toString();
    }

    public static List<String> readByLine(String fileName){
        File file = new File(fileName);
        List<String> fileContent = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                fileContent.add(tempString+" ");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return fileContent;
    }


    public static void writeToFile(String fileName,String content){
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName)),true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        pw.println(content);
    }

}
