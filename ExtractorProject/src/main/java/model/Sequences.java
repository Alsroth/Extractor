package model;

import helper.BuilderHelper;
import helper.FileHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
@Getter
public class Sequences {

    List<Sequence> sequences;

    /**
     * It take a specify type of entry and make it into a list of sequence.
     * @param episodeSplitByLine one line with the episode and the other with timer and subtitle.
     * @return list of sequence
     */
    public void addSequences(String[] episodeSplitByLine) {
        List<Sequence> sequences = new ArrayList<>();
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

        this.sequences.addAll(sequences);
    }

    public void cutAll(Boolean showOutput) throws IOException {
        int compteur = 0;
        System.out.println(sequences);
        for(Sequence s : sequences) {
            s.cut("_edit" + compteur,".mp4",showOutput);
            compteur++;
        }
    }

    public void delayAll(Double second) {
        for(Sequence s: sequences) {
            s.startSequence.delay(second);
            s.endSequence.delay(second);
        }
    }

    public void concatAllOutPutFile() throws IOException {
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
