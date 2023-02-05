package model;

import org.springframework.stereotype.Service;

@Service
public class Settings {

    // Chemin du fichier avec la concaténation de l'ensemble des sous-titres.
    public static final String EXTRACTED_DATA = "D:\\Data\\Projets\\Dev\\Extractor\\onepiece.txt";

    // Chemin vers le dossier bin de l'installation de ffmepg.
    public static final String PATH = "D:\\Logiciels\\Montage\\Ffmpeg\\ffmpeg-5.1.2-essentials_build\\bin";

    // Chemin où se trouve les vidéos sur lequel le projet dois travailler.
    public static final String DIRECTORY = "D:\\Data\\Projets\\Dev\\Extractor";

    // Mot clé via lequel sont extraites les séquences.
    public static final String KEY_WORD = "straw hat";

    // L'extension des vidéos sur lequel le projet dois travailler.
    public static final String EXTENSION_OF_ORIGINE_VIDEO = "mp4";

    // Prefix des vidéos. Les vidéos doivent être nomné par le prefix suivi du numéro de l'épisode en avec
    // au moins deux chiffres.
    public static final String PREFIX_VIDEO = "ep";

    private Settings() {
    }
}
