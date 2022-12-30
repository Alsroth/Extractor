package model;

import lombok.Getter;

@Getter
public class Duration {

    private final int hour;
    private final int minute;
    private final Double seconde;

    Duration(int hour,int minute, Double seconde) {
        this.hour = hour;
        this.minute = minute;
        this.seconde = seconde;
    }
    Duration(String duration) {
        String[] hourMinuteSeconde =  duration.split(":");
        hour = Integer.parseInt(hourMinuteSeconde[0]);
        minute = Integer.parseInt(hourMinuteSeconde[1]);
        seconde = Double.parseDouble(hourMinuteSeconde[2]);
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
