package model;

import helper.BuilderHelper;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Sequence {

    String fileName;
    Duration startSequence;
    Duration endSequence;

    public Sequence(String fileName, String startSequence, String endSequence) {
        this.fileName = fileName;
        this.startSequence = new Duration(startSequence);
        this.endSequence = new Duration(endSequence);
    }

    /**
     * Permet de découper une séquence.
     *
     * @param outputTag  correspond au suffixe ajouté aux vidéos en sortie.
     * @param extension  de la vidéo en sortie.
     * @param showOutput Permet d'afficher la sortie de la commande FFMpeg.
     */
    public void cut(String outputTag, String extension, boolean showOutput) {
        int indexOfDot = fileName.lastIndexOf('.');
        String fileNameWithoutExtension = fileName.substring(0, indexOfDot);
        BuilderHelper.builderStart(new ProcessBuilder("cmd.exe", "/c", "ffmpeg", "-i", fileName, "-map", "0", "-c:v", "libx264", "-c:a"
                        , "aac", "-ss", startSequence.toString()
                        , "-to", endSequence.toString(), fileNameWithoutExtension + outputTag + extension)
                , showOutput);
        History.createdOutPutFiles.add(fileNameWithoutExtension + outputTag + extension);
    }

    /**
     * Permet de décaler le début et la fin d'une séquence.
     * Exemple de décalage de 15 secondes : 00:00:00:00:00:30 --> 00:00:15:00:00:45
     *
     * @param second Le temps en seconde du décalage.
     */
    public void shift(Double second) {
        startSequence.plus(new Duration(0, 0, second));
        endSequence.plus(new Duration(0, 0, second));

    }

    @Override
    public String toString() {
        return "FileName : " + fileName + " Start: " + startSequence.toString() + " End: " + endSequence.toString();
    }

}
