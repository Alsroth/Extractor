package model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class History {

    /**
     * Stock l'ensemble des noms de fichier qui ont été coupé.
     * Cette historique unique sert pour le moment à concaténer l'ensemble des vidéos coupé.
     */
    protected static final List<String> createdOutPutFiles = new ArrayList<>();

    private History() {
    }

}
