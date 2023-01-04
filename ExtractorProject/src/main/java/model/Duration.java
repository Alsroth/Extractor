package model;

import lombok.Getter;

@Getter
public class Duration {

    private int hour;
    private int minute;
    private Double second;

    Duration(int hour,int minute, Double second) {
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

    public void plus(Duration d) {
       Duration result = new Duration(0,0,0.0);
       result.addHour(d.getHour());
       result.addMinute(d.getMinute());
       result.addSecond(d.getSecond());
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
