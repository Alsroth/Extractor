package model;

import helper.BuilderHelper;
import helper.FileHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
@Getter
public class Sequences {

    List<Sequence> sequences;

    public void initFromTextFile() {
        // Je récupére le contenu du fichier dans une chaine de caractères
        String textFile = FileHelper.transformFileIntoString(Settings.extractedData);
        // Je découpe la chaine par épisode.
        String[] fileSplitByEpisode = textFile.split("(?=ep\\d+\\.mp4:)");
        Pattern pattern = Pattern.compile("(.*"+ Settings.keyWord + ".*)|(.*" + Settings.prefixVideo +"\\d+\\." + Settings.extensionOfOrigineVideo +":.*)", Pattern.CASE_INSENSITIVE);
        for (String s : fileSplitByEpisode) {
            // Je découpe ligne par ligne.
            String[] episodeSplitByLine = s.split("\n");
            // Je garde uniquement les lignes qui correspondent à mon expression régulière.
            episodeSplitByLine = Arrays.stream(episodeSplitByLine).filter(text -> pattern.matcher(text).matches()).toArray(String[]::new);
            addSequences(episodeSplitByLine);
        }
    }

    /**
     * It take a specify type of entry and make it into a list of sequence.
     * @param episodeSplitByLine one line with the episode and the other with timer and subtitle.
     * @return list of sequence
     */
    private void addSequences(String[] episodeSplitByLine) {
        String title = episodeSplitByLine[0].split(":")[0];
        Pattern patternEndStartSequence = Pattern.compile("\\d:\\d{2}:\\d{2}\\.\\d{2},\\d:\\d{2}:\\d{2}\\.\\d{2}");
        for (String line : episodeSplitByLine) {
            Matcher matcher = patternEndStartSequence.matcher(line);
            if (matcher.find()) {
                String match = matcher.group();
                String[] timer = match.split(",");
                sequences.add(new Sequence(title,timer[0],timer[1]));
            }
        }
    }

    public void cutAll(Boolean showOutput) {
        int compteur = 0;
        ExecutorService executor = Executors.newFixedThreadPool(2);
        for(Sequence s : sequences) {
            int finalCompteur = compteur;
            executor.execute(() -> {
                try {
                    s.cut("_edit" + finalCompteur,".mp4",showOutput);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            compteur++;
        }
        executor.shutdown();
    }

    public void shiftAll(Double second) {
        for(Sequence s: sequences) {
            s.shift(second);
        }
    }

    public void setAmplitude(Duration d) {
        for(Sequence s: sequences) {
            s.startSequence.minus(d);
            s.endSequence.plus(d);
        }
    }

    public void concatAllOutPutFile() throws IOException, InterruptedException {
        StringBuilder content = new StringBuilder();
        for(String fileName : History.createdOutPutFiles) {
            content.append("file \'").append(fileName).append("\'\n");
        }
        FileHelper.createOrReplaceExistingFile(Settings.directory + "/concatAllOutPutFileScript.txt", content.toString());
        BuilderHelper.builderStart(new ProcessBuilder("cmd.exe","/c","ffmpeg","-f","concat","-safe","0","-i","concatAllOutPutFileScript.txt","-c",
                        "copy","final.mp4")
                ,false);
    }


}
