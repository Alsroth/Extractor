package model;

import helper.BuilderHelper;
import lombok.Getter;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    public void cut(String outputTag, String extension, boolean showOutput) throws InterruptedException {
        int indexOfDot = fileName.lastIndexOf('.');
        String fileNameWithoutExtension = fileName.substring(0, indexOfDot);
        BuilderHelper.builderStart(new ProcessBuilder("cmd.exe", "/c", "ffmpeg", "-i",fileName,"-map","0","-c:v","libx264","-c:a"
                        ,"aac","-ss", startSequence.toString()
                        , "-to", endSequence.toString() , fileNameWithoutExtension + outputTag + extension)
                ,showOutput);
        History.createdOutPutFiles.add(fileNameWithoutExtension + outputTag + extension);
    }

    public void shift(Double second) {
        startSequence.plus(new Duration(0,0,second));
        endSequence.plus(new Duration(0,0,second));

    }

    @Override
    public String toString() {
        return "FileName : " + fileName + " Start: " + startSequence.toString() + " End: " + endSequence.toString();
    }

}
