package test.org.fugerit.java.code.samples.pdfbox2;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.fugerit.java.code.samples.pdfbox2.SpecialCharacters;
import org.fugerit.java.core.lang.helpers.ClassHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/*
 * Proof of Concept to handle special characters like  U+2002 ('enspace')
 *
 * PdfBox bundled font, PDType1Font.HELVETICA will cause an exception :
 * java.lang.IllegalArgumentException: U+2002 ('enspace') is not available in the font Helvetica, encoding: WinAnsiEncoding
 * (see method testWithPdfBoxFontHelvetica)
 *
 * Whereas some externally loaded font with the PDType0Font API will not.
 * (see method testWithExternalHelvetica)
 *
 */
@Slf4j
class TestSpecialCharacters {

    private static final String TEST_LINE = "Row with special character :  .";      // test line with U+2002 ('enspace')

    @Test
    void testWithPdfBoxFontHelvetica() throws IOException {
        try (FileOutputStream fos = new FileOutputStream( "target/test_font_from_pdfbox.pdf" )) {
            SpecialCharacters sp = new SpecialCharacters();
            Assertions.assertThrows( IllegalArgumentException.class, () -> {
                sp.generatePDF( fos, PDType1Font.HELVETICA, TEST_LINE );
            });
        }

    }

    @Test
    void testWithExternalHelvetica() throws IOException {
        File pdfFile = new File( "target/test_font_external.pdf" );
        log.info( "pdfFile: {}, delete: {}", pdfFile, pdfFile.delete() );
        Assertions.assertFalse( pdfFile.exists() );
        try ( InputStream fontIS = ClassHelper.loadFromClassLoader( "font/Helvetica.ttf" );
                FileOutputStream fos = new FileOutputStream( pdfFile ) ) {
            SpecialCharacters sp = new SpecialCharacters();
            sp.generatePDF( fos, fontIS, TEST_LINE );
        }
        Assertions.assertTrue( pdfFile.exists() );
    }

}
