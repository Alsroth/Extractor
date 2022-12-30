package helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileHelper {
    
    /**
     * @return the content of the file into a string
     */
    public static String transformFileIntoString(String path) {
        // Construct a File object with the desired file path
        File file = new File(path);
        String text = null;
        // Open the file
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            // Read the file line by line
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            // Convert the StringBuilder to a String
            text = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return text;
    }


}
