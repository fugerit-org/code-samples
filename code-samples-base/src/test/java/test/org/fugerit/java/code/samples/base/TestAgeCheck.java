package test.org.fugerit.java.code.samples.base;

import lombok.extern.slf4j.Slf4j;
import org.fugerit.java.code.samples.base.AgeCheck;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Slf4j
class TestAgeCheck {

    private boolean testWorkerModule7( LocalDate d1, LocalDate d2 ) {
        boolean result = (ChronoUnit.DAYS.between( d1, d2 )%7==0);
        log.info( "d1 {}, d2 {}, res : {}", d1, d2, result );
        return result;
    }

    @Test
    void testWeek() {
        LocalDate d2 = LocalDate.of( 2024, 8, 1 );
        Assertions.assertTrue( testWorkerModule7( LocalDate.of( 2024, 7, 25 ), d2 ) );
        Assertions.assertFalse( testWorkerModule7( LocalDate.of( 2024, 7, 26 ), d2 ) );
    }

    private boolean check18Worker( LocalDate d ) {
        boolean test = AgeCheck.isAgeAtLeast18( d );
        log.info( "check date: {}, isAgeAtLeast18?: {}", d, test );
        return test;
    }

    @Test
    void test18() {
        LocalDate  d = LocalDate.now();
        Assertions.assertFalse( this.check18Worker( d ) );
        Assertions.assertFalse( this.check18Worker( null ) );   // null is false
        Assertions.assertTrue( this.check18Worker( LocalDate.of( d.getYear()-18, d.getMonthValue(), d.getDayOfMonth()  )  ) ); // test exactly 18 years, true
        Assertions.assertFalse( this.check18Worker( LocalDate.of( d.getYear()-18, d.getMonthValue(), d.getDayOfMonth()+1  )  ) );   // test 18 years -1 day, true
        Assertions.assertTrue( this.check18Worker( LocalDate.of( d.getYear()-30, d.getMonthValue(), d.getDayOfMonth()  )  ) ); // generic test, true
        Assertions.assertFalse( this.check18Worker( LocalDate.of( d.getYear()-5, d.getMonthValue(), d.getDayOfMonth()  )  ) );  // generic test, false
    }

}
