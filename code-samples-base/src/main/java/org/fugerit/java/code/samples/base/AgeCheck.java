package org.fugerit.java.code.samples.base;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class AgeCheck {

    private AgeCheck() {}

    public static Boolean isAgeAtLeast(LocalDate d, int numberOfYears) {
        return d != null &&  Period.between(d, LocalDate.now()).getYears() >=numberOfYears;
    }

    public static Boolean isAgeAtLeast18(LocalDate d) {
        return isAgeAtLeast(d, 18);
    }

}
