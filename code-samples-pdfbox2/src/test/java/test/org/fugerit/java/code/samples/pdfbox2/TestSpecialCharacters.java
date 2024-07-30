package test.org.fugerit.java.code.samples.pdfbox2;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.fugerit.java.code.samples.pdfbox2.SpecialCharacters;
import org.fugerit.java.core.lang.helpers.ClassHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

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

    private static final String TEST_LINE = "Row with special character : %s.";      // test line with U+2002 ('enspace')

    private static final String[] SPECIAL_CHARACTERS = {
            "\u2002",   // 'enspace'
            "\u2010",   // 'hyphentwo'
            "\u2033",   // 'second'
            "\u03BC",   // 'mugreek'
            "\u039C",   // 'Mu'
            "\u2212",   // 'minus'
            "\u0141" ,  // 'Lslash'
            "\u2103",   // 'centigrade'
    };

    @Test
    void testWithPdfBoxFontHelvetica() throws IOException {
        SpecialCharacters sp = new SpecialCharacters();
        for ( int k=0; k<SPECIAL_CHARACTERS.length; k++ ) {
            String special = SPECIAL_CHARACTERS[k];
            String line = String.format( TEST_LINE, special );
            try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
                log.info( "testWithPdfBoxFontHelvetica index:{}, char:{}, line:{}", k, special, line );
                Assertions.assertThrows(IllegalArgumentException.class, () -> {
                    sp.generatePDF(os, PDType1Font.HELVETICA, line);
                });
            }
        }
    }

    @Test
    void testWithExternalHelvetica() throws IOException {
        // some characters are still not available on all font, for instance in this example :
        // "\uFFFD",   // '.notdef'
        // "\u25AA",   // 'H18543'
        SpecialCharacters sp = new SpecialCharacters();
        for ( int k=0; k<SPECIAL_CHARACTERS.length; k++ ) {
            String special = SPECIAL_CHARACTERS[k];
            String line = String.format( TEST_LINE, special );
            File pdfFile = new File( String.format( "target/test_font_external_%s.pdf", k ) );
            log.info( "pdfFile: {}, delete: {}", pdfFile, pdfFile.delete() );
            Assertions.assertFalse( pdfFile.exists() );
            try (InputStream fontIS = ClassHelper.loadFromClassLoader( "font/Helvetica.ttf" );
                 OutputStream os = new FileOutputStream( pdfFile ) ) {
                log.info( "testWithExternalHelvetica index:{}, char:{}, line:{}", k, special, line );
                sp.generatePDF(os, fontIS, line);
            }
            Assertions.assertTrue( pdfFile.exists() );
        }
    }

    @Test
    void testWithPdfBoxFontHelveticaNormalLine() throws IOException {
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            SpecialCharacters sp = new SpecialCharacters();
            sp.generatePDF( os, PDType1Font.HELVETICA, "Normal line" );
            Assertions.assertNotEquals( 0, os.toByteArray().length );
        }
    }

}
