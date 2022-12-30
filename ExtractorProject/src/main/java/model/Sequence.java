package model;

import lombok.Getter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

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
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "ffmpeg", "-ss", startSequence.toString() , "-to", endSequence.toString() , "-i", fileName, "-c", "copy", fileNameWithoutExtension + outputTag + extension);
        builder.environment().put("Path","D:\\Logiciels\\Montage\\Ffmpeg\\ffmpeg-5.1.2-essentials_build\\bin");
        // Start the process and get the process object
        builder.directory(new File("D:\\Data\\Projets\\Dev\\Extractor"));
        builder.redirectErrorStream(true);
        Process process = builder.start();

        if (showOutput) {
            // Get the input stream of the process and create a BufferedReader to read its output
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            // Read the output of the process line by line and print it to the console
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    @Override
    public String toString() {
        return "FileName : " + fileName + " Start: " + startSequence.toString() + " End: " + endSequence.toString();
    }

}
