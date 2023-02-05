package model;

import exception.ProcessBuilderException;
import helper.BuilderHelper;
import helper.FileHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@AllArgsConstructor
@Getter
public class Sequences {

    private final Logger logger = Logger.getLogger("Sequences");

    List<Sequence> trame;

    /**
     * Lis un fichier texte composé de ligne avec des titres des episodes suivi de leur sous-titre. Il est utilisé pour extraire
     * l'ensemble des séquences par épisode correspondant à un mot clé retrouvé dans les sous-titres.
     */
    public void initFromTextFile() {
        // Je récupère le contenu du fichier dans une chaine de caractères
        String textFile = FileHelper.transformFileIntoString(Settings.EXTRACTED_DATA);
        // Je découpe la chaine par épisode.
        String[] fileSplitByEpisode = textFile.split("(?=ep\\d+\\.mp4:)");
        Pattern pattern = Pattern.compile("(.*" + Settings.KEY_WORD + ".*)|(.*" + Settings.PREFIX_VIDEO + "\\d+\\." + Settings.EXTENSION_OF_ORIGINE_VIDEO + ":.*)", Pattern.CASE_INSENSITIVE);
        for (String s : fileSplitByEpisode) {
            // Je découpe ligne par ligne.
            String[] episodeSplitByLine = s.split("\n");
            // Je garde uniquement les lignes qui correspondent à mon expression régulière.
            episodeSplitByLine = Arrays.stream(episodeSplitByLine).filter(text -> pattern.matcher(text).matches()).toArray(String[]::new);
            addSequences(episodeSplitByLine);
        }
    }

    /**
     * Prends une entrée spécifique pour la convertir en séquences.
     *
     * @param episodeSplitByLine Tableau où chaque elements comporte une ligne avec le nom des episodes suivi de lignes correspondant au sequences
     *                           à extraire.
     */
    private void addSequences(String[] episodeSplitByLine) {
        String title = episodeSplitByLine[0].split(":")[0];
        Pattern patternEndStartSequence = Pattern.compile("\\d:\\d{2}:\\d{2}\\.\\d{2},\\d:\\d{2}:\\d{2}\\.\\d{2}");
        for (String line : episodeSplitByLine) {
            Matcher matcher = patternEndStartSequence.matcher(line);
            if (matcher.find()) {
                String match = matcher.group();
                String[] timer = match.split(",");
                trame.add(new Sequence(title, timer[0], timer[1]));
            }
        }
    }

    /**
     * Permet de couper l'ensemble des sequences.
     */
    public void cutAll() {
        int compteur = 0;
        ExecutorService executor = Executors.newFixedThreadPool(1);
        for (Sequence s : trame) {
            int finalCompteur = compteur;
            Runnable thread = () -> {
                try {
                    s.cut("_edit" + finalCompteur, ".mp4");
                } catch (ProcessBuilderException e) {
                    logger.warning("" + e);
                }
            };
            executor.submit(thread);
            compteur++;
        }
        executor.shutdown();
    }

    /**
     * Décale la séquence d'un nombre donnée de secondes.
     *
     * @param second nombre de secondes correspondantes au décalage du départ et de la fin de la séquence.
     */
    public void shiftAll(Double second) {
        for (Sequence s : trame) {
            s.shift(second);
        }
    }

    /**
     * Ajoute une durée au début et à la fin d'une séquence comme ceci pour 30 min par exemple :
     * 1:00:00 - 1:30:00 -> 00:30:00 - 2:00:00
     *
     * @param d Duration. La durée de laquelle est étendue la séquence vers le haut et le bas.
     */
    public void setAmplitude(Duration d) {
        for (Sequence s : trame) {
            s.getStartSequence().minus(d);
            s.getEndSequence().plus(d);
        }
    }

    /**
     * Créer une vidéo contenant l'ensemble des vidéos découpée en une seule vidéo.
     */
    public void concatAllOutPutFile() throws ProcessBuilderException {
        StringBuilder content = new StringBuilder();
        for (String fileName : History.createdOutPutFiles) {
            content.append("file '").append(fileName).append("'\n");
        }
        FileHelper.createOrReplaceExistingFile(Settings.DIRECTORY + "/concatAllOutPutFileScript.txt", content.toString());
        BuilderHelper.builderStart(new ProcessBuilder("cmd.exe", "/c", "ffmpeg", "-f", "concat", "-safe", "0", "-i", "concatAllOutPutFileScript.txt", "-c",
                "copy", "final.mp4"));
    }


}
