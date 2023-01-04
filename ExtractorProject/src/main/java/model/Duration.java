package model;

import lombok.Getter;

@Getter
public class Duration {

    private int hour;
    private int minute;
    private Double second = 0.0;

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

    public Duration minus(Duration d) {
        return new Duration(hour - d.getHour(), minute - d.getMinute(), second - d.getSecond());
    }

    private String twoDigitNumber(Number n)  {
        if (n.intValue() < 10) {
           return "0" + n;
        }
        return "" +  n;
    }

    public void delay(Double secondToDelay) {
        if ((second + secondToDelay) > 60 && minute < 59) {
            minute += 1;
            second = ((second + 16.5) - 60);
        }
        else if((second + secondToDelay) > 60 && minute == 59) {
            hour += 1;
            minute = 0;
            second = ((second + 16.5) - 60);
        }
        else {
            second += 16.5;
        }
    }

    @Override
    public String toString() {
        return twoDigitNumber(hour) + ":" + twoDigitNumber(minute) + ":" + twoDigitNumber(second);
    }
}
