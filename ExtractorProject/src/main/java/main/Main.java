package main;


import model.Duration;
import model.Sequences;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class Main {


    public static void main(String[] args) {
        Sequences sequences = new Sequences(new ArrayList<>());
        sequences.initFromTextFile();
        sequences.setAmplitude(new Duration(0, 0, 20.0));
        sequences.cutAll(true);
    }
}
