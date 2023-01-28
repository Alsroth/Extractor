package helper;

import model.Settings;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class BuilderHelper {

    private BuilderHelper() {
    }

    /**
     * Permet de lancer un processBuilder récupérer en paramètre et
     * d'en afficher sa sortie optionnellement.
     *
     * @param builder    ProcessBuilder qui contient une commande à exécuter.
     * @param showOutput Permet d'affiche la sortie du terminal ouvert.
     */
    public static void builderStart(ProcessBuilder builder, boolean showOutput) {
        builder.environment().put("Path", Settings.path);
        // Start the process and get the process object
        builder.directory(new File(Settings.directory));
        builder.redirectErrorStream(true);
        Process process;
        try {
            process = builder.start();
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
                if (showOutput) {
                    System.out.println(line);
                }
            }
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
