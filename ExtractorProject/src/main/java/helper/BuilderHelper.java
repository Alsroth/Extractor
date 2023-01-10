package helper;

import model.Settings;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BuilderHelper {

    private BuilderHelper() {}

    public static void builderStart(ProcessBuilder builder,boolean showOutput) throws InterruptedException {
            Thread thread = new Thread(() -> {
                builder.environment().put("Path", Settings.path);
                // Start the process and get the process object
                builder.directory(new File(Settings.directory));
                builder.redirectErrorStream(true);
                Process process = null;
                try {
                    process = builder.start();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                if (showOutput) {
                    // Get the input stream of the process and create a BufferedReader to read its output
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                    // Read the output of the process line by line and print it to the console
                    String line;
                    while (true) {
                        try {
                            if ((line = reader.readLine()) == null) break;
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println(line);
                    }
                }
            });
        thread.start();
        thread.join();
    }
}
