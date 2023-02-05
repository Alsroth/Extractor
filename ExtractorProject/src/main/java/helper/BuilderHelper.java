package helper;

import exception.ProcessBuilderException;
import model.Settings;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.logging.Logger;

@Component
public class BuilderHelper {

    private static final Logger LOGGER = Logger.getLogger("terminal");

    private BuilderHelper() {
    }

    /**
     * Permet de lancer un processBuilder récupérer en paramètre et
     * d'en afficher sa sortie.
     *
     * @param builder rocessBuilder qui contient une commande à exécuter.
     */
    public static void builderStart(ProcessBuilder builder) throws ProcessBuilderException {
        builder.environment().put("Path", Settings.PATH);
        // Start the process and get the process object
        builder.directory(new File(Settings.DIRECTORY));
        builder.redirectErrorStream(true);
        Process process;
        try {
            process = builder.start();
            readInputStream(process.getInputStream());
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            LOGGER.warning("Interrupted!, error: " + e);
            Thread.currentThread().interrupt();
            throw new ProcessBuilderException();
        }
    }

    private static void readInputStream(InputStream input) throws ProcessBuilderException {
        // Get the input stream of the process and create a BufferedReader to read its output
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        // Read the output of the process line by line and print it to the console
        String line;
        while (true) {
            try {
                if ((line = reader.readLine()) == null) break;
            } catch (IOException e) {
                throw new ProcessBuilderException();
            }
            LOGGER.info(line);
        }
    }
}
