package test.org.fugerit.java.code.samples.base;

import lombok.extern.slf4j.Slf4j;
import org.fugerit.java.code.samples.base.AgeCheck;
import org.fugerit.java.code.samples.base.PeriodCheck;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Slf4j
class TestPeriodCheck {

    @Test
    void testWeekCheck() {
        Assertions.assertTrue(PeriodCheck.everyWeeekFrom( LocalDate.of( 2024, 7, 25 ), LocalDate.of( 2024, 8, 8 ) ) );
        Assertions.assertTrue(PeriodCheck.everyWeeekFrom( LocalDate.of( 2024, 8, 1 ), LocalDate.of( 2024, 8, 8 ) ) );
        Assertions.assertFalse(PeriodCheck.everyWeeekFrom( LocalDate.of( 2024, 7, 26 ), LocalDate.of( 2024, 8, 8 ) ) );
    }

}
