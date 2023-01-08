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
        long debut = System.currentTimeMillis();
        Sequences sequences = new Sequences(new ArrayList<>());
        sequences.initFromTextFile();
        sequences.setAmplitude(new Duration(0,0, 20.0));
        // sequences.shiftAll(16.0);
        sequences.cutAll(false);
        // sequences.concatAllOutPutFile();

        long fin = System.currentTimeMillis();
        long tempsExecution = fin - debut;

        System.out.println("Le code a mis " + tempsExecution + " millisecondes à s'exécuter.");
    }
}
