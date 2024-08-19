package org.fugerit.java.code.samples.base;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class HourCheck {

    private HourCheck() {}

    public static Boolean isNoMoreHoursFrom(LocalDateTime d, int numberOfHours) {
        return d != null && d.until(LocalDateTime.now(), ChronoUnit.HOURS)<=numberOfHours;
    }

}
