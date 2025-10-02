package org.fugerit.java.code.samples.tiff;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

@Slf4j
class TiffValidatorTest {

    @Test
    void testValidTiff() {
        File sampleFile = new File("src/test/resources/sample/sample.tiff");
        boolean valid = TiffValidator.isValidTiff( sampleFile );
        log.info( "validation result : {} -> {}", valid, sampleFile.getAbsolutePath() );
        Assertions.assertTrue(valid);
    }

}
