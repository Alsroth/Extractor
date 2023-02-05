package helper;

import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FileHelper {

    private FileHelper() {
    }

    /**
     * Permet de récupérer le contenu d'un fichier texte pour le transformer en une variable de chaine de caractères.
     *
     * @param path Chemin du fichier à convertir en String.
     * @return Retourne le contenu d'un fichier texte en un objet String.
     */
    public static String transformFileIntoString(String path) {
        // Construct a File object with the desired file path
        File file = new File(path);
        String text = null;
        // Open the file
        try (BufferedReader reader = new BufferedReader(new FileReader(file), 1024 * 8)) {
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

    /**
     * Créer ou remplace un fichier avec le contenu passé en paramètre.
     *
     * @param fileName Nom du fichier dans lequel écrire.
     * @param content  Contenu à écrire dans le fichier.
     */
    public static void createOrReplaceExistingFile(String fileName, String content) {
        try (FileWriter fw = new FileWriter(fileName)) {
            fw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
