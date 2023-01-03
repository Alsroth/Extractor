package helper;

import model.Settings;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class BuilderHelper {

    public static void builderStart(ProcessBuilder builder,Boolean showOutput) throws IOException {
        builder.environment().put("Path", Settings.path);
        // Start the process and get the process object
        builder.directory(new File(Settings.directory));
        builder.redirectErrorStream(true);
        Process process = builder.start();

        if (showOutput) {
            // Get the input stream of the process and create a BufferedReader to read its output
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            // Read the output of the process line by line and print it to the console
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
