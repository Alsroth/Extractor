package model;

import org.springframework.stereotype.Service;

@Service
public class Settings {

    private Settings() {}

    // Chemin du fichier avec la concaténation de l'ensemble des sous-titres.
    public static String extractedData = "D:\\Data\\Projets\\Dev\\Extractor\\onepiece.txt";

    // Chemin vers le dossier bin de l'installation de ffmepg.
    public static String path = "D:\\Logiciels\\Montage\\Ffmpeg\\ffmpeg-5.1.2-essentials_build\\bin";

    // Chemin où se trouve les vidéos sur lequel le projet dois travailler.
    public static String directory = "D:\\Data\\Projets\\Dev\\Extractor";

    // Mot clé via lequel sont extrait les séquences.
    public static String keyWord = "straw hat";

    // L'extension des vidéos sur lequel le projet dois travailler.
    public static String extensionOfOrigineVideo = "mp4";

    // Prefix des vidéos. Les vidéos doivent être nomné par le prefix suivi du numéro de l'episode en avec
    // au moins deux chiffres.
    public static String prefixVideo = "ep";
}
