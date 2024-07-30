package test.org.fugerit.java.code.samples.pdfbox2;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.fugerit.java.code.samples.pdfbox2.LineWrapping;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

/*
 * Proof of Concept to handle PDFBox line wrapping.
 *
 * PDFBox is a low level library and does not wrap text OOTB.
 *
 *
 */
@Slf4j
class TestLineWrapping {

    private static final String[] LINES = {
            "Row 1 test",
            "Row 2 test, this is a long line that needs line wrapping, let's see how it works. Hopefully this line will show up on two lines.",
            "Row 3 test",
    };

    @Test
    void testLineWrapping() throws IOException {
        LineWrapping handler = new LineWrapping();
        File pdfFile = new File( "target/test_line_wrapping.pdf" );
        log.info( "pdfFile: {}, delete: {}", pdfFile, pdfFile.delete() );
        Assertions.assertFalse( pdfFile.exists() );
        try (OutputStream os = new FileOutputStream( pdfFile ) ) {
            handler.generatePDF(os, PDType1Font.HELVETICA, LINES );
        }
        Assertions.assertTrue( pdfFile.exists() );
    }

}
