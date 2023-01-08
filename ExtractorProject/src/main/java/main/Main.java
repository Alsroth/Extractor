package main;

import helper.FileHelper;
import model.Duration;
import model.Settings;
import model.Sequences;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class  Main {


    public static void main(String[] args) throws IOException {
        Sequences sequences = new Sequences(new ArrayList<>());
        sequences.initFromTextFile();
        //sequences.shiftAll(16.0);
        sequences.cutAll(false);
        // sequences.concatAllOutPutFile();
    }
}
