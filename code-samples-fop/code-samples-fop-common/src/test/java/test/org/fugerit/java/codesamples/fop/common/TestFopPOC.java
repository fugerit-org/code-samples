package test.org.fugerit.java.codesamples.fop.common;

import lombok.extern.slf4j.Slf4j;
import org.fugerit.java.codesamples.fop.common.FopPOC;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
class TestFopPOC {

    @Test
    void testFopPOC() throws IOException {
        File outputFile = new File( "target", "pdf-a-test-1.pdf" );
        try (FileOutputStream fos = new FileOutputStream( outputFile )) {
            FopPOC.generatePdfATest1( fos );
            Assertions.assertTrue( outputFile.exists() );
        }
    }

}