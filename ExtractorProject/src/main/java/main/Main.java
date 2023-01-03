package main;

import helper.FileHelper;
import model.Settings;
import model.Sequences;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Main {


    public static void main(String[] args) throws IOException {
        // Je récupére le contenu du fichier dans une chaine de caractères
        String textFile = FileHelper.transformFileIntoString(Settings.extractedData);
        // Je découpe la chaine par épisode.
        String[] fileSplitByEpisode = textFile.split("(?=ep\\d+\\.mp4:)");


        Pattern pattern = Pattern.compile("(.*straw hat.*)|(.*ep\\d+\\.mp4:.*)");
        Sequences sequences = new Sequences(new ArrayList<>());
        for (String s : fileSplitByEpisode) {
            // Je découpe ligne par ligne.
            String[] episodeSplitByLine = s.split("\n");
            // Je garde uniquement les lignes qui correspondent à mon expression régulière.
            episodeSplitByLine = Arrays.stream(episodeSplitByLine).filter(text -> pattern.matcher(text).matches()).toArray(String[]::new);
            sequences.addSequences(episodeSplitByLine);
        }

        sequences.cutAll(false);
        sequences.concatAllOutPutFile();
    }
}
