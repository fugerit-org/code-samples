package org.fugerit.java.code.samples.pdfbox2;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class AgeCheck {

    private AgeCheck() {}

    public static Boolean isAgeAtLeast(LocalDate d, int numberOfYears) {
        return d != null && d.until(LocalDate.now(), ChronoUnit.YEARS)>=numberOfYears;
    }

    public static Boolean isAgeAtLeast18(LocalDate d) {
        return isAgeAtLeast(d, 18);
    }

}
