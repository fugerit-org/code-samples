package org.fugerit.java.code.samples.base;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PeriodCheck {

    private PeriodCheck() {}

    public static Boolean everyNumberOfDaysDateFrom(LocalDate fromDate, LocalDate testDate, int numberOfDays) {
        return ChronoUnit.DAYS.between(fromDate, testDate) % numberOfDays == 0;
    }


    public static Boolean everyWeekFrom(LocalDate fromDate, LocalDate testDate) {
        return everyNumberOfDaysDateFrom(fromDate, testDate, 7);
    }

}
