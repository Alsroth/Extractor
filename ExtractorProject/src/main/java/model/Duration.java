package model;

import lombok.Getter;

@Getter
public class Duration {

    private final int hour;
    private int minute;
    private Double seconde = 0.0;

    Duration(int hour,int minute, Double seconde) {
        this.hour = hour;
        this.minute = minute;
        this.seconde = seconde;
        if ((seconde + 16) > 60) {
            this.minute += 1;
            this.seconde += ((seconde + 16) - 60);
        } else {
            this.seconde += 16;
        }

    }
    Duration(String duration) {
        String[] hourMinuteSeconde =  duration.split(":");
        hour = Integer.parseInt(hourMinuteSeconde[0]);
        minute = Integer.parseInt(hourMinuteSeconde[1]);
        seconde = Double.parseDouble(hourMinuteSeconde[2]);
        if ((seconde + 16.5) > 60) {
            this.minute += 1;
            this.seconde = ((seconde + 16.5) - 60);
        } else {
            this.seconde += 16.5;
        }
    }

    public Duration minus(Duration d) {
        return new Duration(hour - d.getHour(), minute - d.getMinute(), seconde - d.getSeconde());
    }

    private String twoDigitNumber(Number n)  {
        if (n.intValue() < 10) {
           return "0" + n;
        }
        return "" +  n;
    }

    @Override
    public String toString() {
        return twoDigitNumber(hour) + ":" + twoDigitNumber(minute) + ":" + twoDigitNumber(seconde);
    }
}
