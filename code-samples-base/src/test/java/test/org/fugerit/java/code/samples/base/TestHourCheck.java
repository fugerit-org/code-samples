package test.org.fugerit.java.code.samples.base;

import lombok.extern.slf4j.Slf4j;
import org.fugerit.java.code.samples.base.HourCheck;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
class TestHourCheck {

    @Test
    void testHours() {
        LocalDate  d = LocalDate.now();
        Assertions.assertTrue(
                HourCheck.isNoMoreHoursFrom(LocalDateTime.of( d.getYear(), d.getMonthValue(), d.getDayOfMonth(), 1, 1, 1 ), 336 ) );
        Assertions.assertFalse(
                HourCheck.isNoMoreHoursFrom(LocalDateTime.of( d.getYear()-1, d.getMonthValue(), d.getDayOfMonth(), 1, 1, 1 ), 336 ) );
        Assertions.assertFalse(
                HourCheck.isNoMoreHoursFrom( null, 336 ) );
    }

}
