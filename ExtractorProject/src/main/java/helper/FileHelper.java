package helper;

import java.io.*;

public class FileHelper {

    private FileHelper() {}

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

    public static void createOrReplaceExistingFile(String fileName, String content) {
        try {
            FileWriter fw = new FileWriter(fileName);
            fw.write(content);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
