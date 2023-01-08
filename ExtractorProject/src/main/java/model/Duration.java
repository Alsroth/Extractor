package model;

import lombok.Getter;

import javax.naming.Context;

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
        String[] hourMinuteSecond =  duration.split(":");
        hour = Integer.parseInt(hourMinuteSecond[0]);
        minute = Integer.parseInt(hourMinuteSecond[1]);
        second = Double.parseDouble(hourMinuteSecond[2]);
    }

    private String twoDigitNumber(Number n)  {
        if (n.intValue() < 10) {
           return "0" + n;
        }
        return "" +  n;
    }

    public void minus(Duration d) {
        this.soustractHour(d.getHour());
        this.soustractMinute(d.getMinute());
        this.soustractSecond(d.getSecond());
    }

    private void soustractHour(int hourToSoustract) {
        if ((hour - hourToSoustract) < 0) {
            System.err.println("La soustraction des heures ne peux pas être négatif");
        } else {
            hour -= hourToSoustract;
        }
    }

    private void soustractMinute(int minuteToSoustract) {
        if ((minute - minuteToSoustract) < 0) {
            soustractHour(1);
            minute = Math.abs(minute - minuteToSoustract);
        } else {
            minute -= minuteToSoustract;
        }
    }

    private void soustractSecond(double secondToSoustract) {
        if ((second - secondToSoustract) < 0) {
            soustractMinute(1);
            second = Math.abs(second  - secondToSoustract);
        } else {
            second -= secondToSoustract;
        }
    }

    public void plus(Duration d) {
       this.addHour(d.getHour());
       this.addMinute(d.getMinute());
       this.addSecond(d.getSecond());
    }

    private void addHour(int hourToAdd) {
        if(hourToAdd + hour < 24) {
            hour += hourToAdd;
        } else {
            System.err.println("Les heures dépasse 24 heures si on ajoute :" +  hourToAdd);
        }
    }

    private void addMinute(int minuteToAdd) {
        if ((minute + minuteToAdd) >= 60) {
            hour += 1;
            minute = (minute + minuteToAdd) - 60;
        }
        else {
            minute += minuteToAdd;
        }
    }

    private void addSecond(Double secondToAdd) {
        if ((second + secondToAdd) >= 60 && minute < 59) {
            minute += 1;
            second = ((second + secondToAdd) - 60);
        }
        else if((second + secondToAdd) >= 60 && minute == 59) {
            hour += 1;
            minute = 0;
            second = ((second + secondToAdd) - 60);
        }
        else {
            second += secondToAdd;
        }
    }

    @Override
    public String toString() {
        return twoDigitNumber(hour) + ":" + twoDigitNumber(minute) + ":" + twoDigitNumber(second);
    }
}
