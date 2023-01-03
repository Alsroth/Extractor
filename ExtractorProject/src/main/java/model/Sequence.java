package model;

import helper.BuilderHelper;
import lombok.Getter;

import java.io.IOException;

@Getter
public class Sequence {

    String fileName;
    Duration startSequence;
    Duration endSequence;
    Duration duration;

    public Sequence(String fileName, String startSequence, String endSequence) {
        this.fileName = fileName;
        this.startSequence = new Duration(startSequence);
        this.endSequence = new Duration(endSequence);
        this.duration = this.endSequence.minus(this.startSequence);

    }

    public void cut(String outputTag, String extension, boolean showOutput)  throws IOException {
        int indexOfDot = fileName.lastIndexOf('.');
        String fileNameWithoutExtension = fileName.substring(0, indexOfDot);
        BuilderHelper.builderStart(new ProcessBuilder("cmd.exe", "/c", "ffmpeg", "-ss", startSequence.toString()
                        , "-to", endSequence.toString() , "-i", fileName, "-c", "copy", fileNameWithoutExtension + outputTag + extension)
                ,false);
        History.createdOutPutFiles.add(fileNameWithoutExtension + outputTag + extension);
    }

    @Override
    public String toString() {
        return "FileName : " + fileName + " Start: " + startSequence.toString() + " End: " + endSequence.toString();
    }

}
