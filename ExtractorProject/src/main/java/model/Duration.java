package model;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Getter
public class Duration {

    private int hour;
    private int minute;
    private Double second;

    public Duration(int hour, int minute, Double second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    Duration(String duration) {
        String[] hourMinuteSecond = duration.split(":");
        hour = Integer.parseInt(hourMinuteSecond[0]);
        minute = Integer.parseInt(hourMinuteSecond[1]);
        second = Double.parseDouble(hourMinuteSecond[2]);
    }

    /**
     * Permet d'afficher les chiffres avec un zéro.
     * Exemple : 0->00; 5-->05
     *
     * @param n Le nombre à mettre si nécessaire avec deux chiffres.
     * @return retourne le nombre sous forme de chaine de caractères.
     */
    private String twoDigitNumber(Number n) {
        if (n.intValue() < 10) {
            return "0" + n;
        }
        return "" + n;
    }

    /**
     * Permet de soustraire une durée.
     * Si la durée à soustraire est supérieur à celle de l'objet alors la durée aura pour valeur : 00:00:00
     *
     * @param d une durée
     */
    public void minus(Duration d) {
        this.soustractHour(d.getHour());
        this.soustractMinute(d.getMinute());
        this.soustractSecond(d.getSecond());
    }

    /**
     * Permet de soustraire une heure à la durée.
     *
     * @param hourToSoustract heure à soustraire.
     * @return retourne true si le résultat de la soustraction est positive.
     * Autrement renvoi false avec la durée à 00:00:00.
     */
    private boolean soustractHour(int hourToSoustract) {
        if ((hour - hourToSoustract) < 0) {
            hour = 0;
            minute = 0;
            second = 0.0;
            return false;
        } else {
            hour -= hourToSoustract;
            return true;
        }
    }

    /**
     * Permet de soustraire des minutes à la durée.
     *
     * @param minuteToSoustract minutes à soustraire.
     * @return retourne true si le résultat de la soustraction est positive.
     * Autrement renvoi false avec la durée à 00:00:00.
     */
    private boolean soustractMinute(int minuteToSoustract) {
        if ((minute - minuteToSoustract) < 0) {
            boolean isSoustractionPositive = soustractHour(1);
            if (isSoustractionPositive) {
                minute = 60 + minute - minuteToSoustract;
            }
            return isSoustractionPositive;
        } else {
            minute -= minuteToSoustract;
        }
        return true;
    }

    /**
     * Permet de soustraire des secondes à la durée.
     *
     * @param secondToSoustract secondes à soustraire.
     */
    private void soustractSecond(double secondToSoustract) {
        if ((second - secondToSoustract) < 0) {
            boolean isSoustractionPositive = soustractMinute(1);
            if (isSoustractionPositive) {
                second = 60 + second - secondToSoustract;
            }
        } else {
            second -= secondToSoustract;
        }
    }

    /**
     * Permet d'ajouter d'additionner une durée.
     * Si la durée dépasse 24 h une erreur est affichée dans la sortie d'erreur.
     *
     * @param d durée à additionner.
     */
    public void plus(Duration d) {
        this.addHour(d.getHour());
        this.addMinute(d.getMinute());
        this.addSecond(d.getSecond());
    }

    /**
     * Permet d'ajouter des heures à la durée.
     *
     * @param hourToAdd heures à ajouter.
     */
    private void addHour(int hourToAdd) {
        hour += hourToAdd;
    }

    /**
     * Permet d'ajouter des minutes à la durée.
     *
     * @param minuteToAdd Minute à ajouter à la durée.
     */
    private void addMinute(int minuteToAdd) {
        if ((minute + minuteToAdd) >= 60) {
            hour += 1;
            minute = (minute + minuteToAdd) - 60;
        } else {
            minute += minuteToAdd;
        }
    }

    /**
     * Permet d'ajouter des secondes à la durée
     *
     * @param secondToAdd secondes à ajouter.
     */
    private void addSecond(Double secondToAdd) {
        if ((second + secondToAdd) >= 60 && minute < 59) {
            minute += 1;
            second = ((second + secondToAdd) - 60);
        } else if ((second + secondToAdd) >= 60 && minute == 59) {
            hour += 1;
            minute = 0;
            second = ((second + secondToAdd) - 60);
        } else {
            second += secondToAdd;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Duration duration = (Duration) o;
        return hour == duration.hour && minute == duration.minute && Objects.equals(second, duration.second);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return twoDigitNumber(hour) + ":" + twoDigitNumber(minute) + ":" + twoDigitNumber(second);
    }
}
