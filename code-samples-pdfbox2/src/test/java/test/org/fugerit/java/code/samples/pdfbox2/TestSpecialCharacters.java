package test.org.fugerit.java.code.samples.pdfbox2;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.fugerit.java.code.samples.pdfbox2.SpecialCharacters;
import org.fugerit.java.core.lang.helpers.ClassHelper;
import org.fugerit.java.core.util.PropsIO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            "\u27E8",   // '⟨',
            "\u27E9",   // '⟩',
    };

    private static final String HELVETICA = "Helvetica";
    private static final String DEJAVUSANS = "DejaVuSans";
    private static final String TEXGYREHEROS = "texgyreheros-regular";
    private static final String NOTOSANS = "NotoSans-Regular";
    private static final String IBMPLEXSANS = "IBMPlexSans-Regular";

    private static final String[] TTF_LIST = {
            HELVETICA,
            DEJAVUSANS,
            TEXGYREHEROS,
            NOTOSANS,
            IBMPLEXSANS,
    };

    private static final Map<String, Integer> ERRORI;
    static {
        ERRORI = new HashMap<>();
        ERRORI.put(HELVETICA, 2);
        ERRORI.put(DEJAVUSANS, 0);
        ERRORI.put(TEXGYREHEROS, 4);
        ERRORI.put(NOTOSANS, 2);
        ERRORI.put(IBMPLEXSANS, 3);
    }

    private static final Properties PATTERNS = PropsIO.loadFromClassLoaderSafe( "font/patterns.properties" );

    @Test
    void testWithPdfBoxFontHelvetica() throws IOException {
        SpecialCharacters sp = new SpecialCharacters();
        String[] specials = SPECIAL_CHARACTERS;
        for ( int k=0; k<specials.length; k++ ) {
            String special = specials[k];
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
    void testWithExternalFonts() throws IOException {
        SpecialCharacters sp = new SpecialCharacters();
        for( String ttf : TTF_LIST ) {
            String fontPath = String.format( "font/%s.ttf", ttf );
            String[] specials = SPECIAL_CHARACTERS;
            int countErrors = 0;
            StringBuilder errorList = new StringBuilder();
            for ( int k=0; k<specials.length; k++ ) {
                String special = specials[k];
                String unicode = String.format( "u%04X", (int)special.charAt( 0 ) );
                String line = String.format( TEST_LINE, special );
                File pdfFile = new File( String.format( "target/test_font_%s_%s.pdf", ttf, unicode ) );
                log.info( "pdfFile: {}, delete: {}", pdfFile, pdfFile.delete() );
                Assertions.assertFalse( pdfFile.exists() );
                try (InputStream fontIS = ClassHelper.loadFromClassLoader( fontPath );
                     OutputStream os = new FileOutputStream( pdfFile ) ) {
                    log.info( "test '{}' index:{}, char:{}, line:{}", ttf, k, special, line );
                    sp.generatePDF(os, fontIS, line);
                } catch (Exception e) {
                    log.error( "error on font : {} - {}", ttf, e.getMessage() );
                    pdfFile.delete();
                    errorList.append( '\\' );
                    errorList.append( unicode );
                    countErrors++;
                }
            }
            log.info( "font : {}, countErrors: {}, errorList : [{}]", ttf, countErrors, errorList );
            Assertions.assertEquals( ERRORI.get( ttf ), countErrors );
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

    private boolean check( String regex, String input ) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    private static final String TEST_STRING_1 = "\u27E8 formula \u27E9";

    private static final String TEST_STRING_2 = "\u2002 test enscape";

    @Test
    void testHelvetica() {
        String testString1 = TEST_STRING_1;
        log.info( "testString1 : {}", testString1 );
        Assertions.assertTrue( check( PATTERNS.getProperty( HELVETICA ), testString1 ) );
        Assertions.assertFalse( check( PATTERNS.getProperty( DEJAVUSANS ), testString1 ) );
        String testString2 = TEST_STRING_2;
        log.info( "testString2 : {}", testString2 );
        Assertions.assertTrue( check( PATTERNS.getProperty( TEXGYREHEROS ), testString2 ) );
        Assertions.assertFalse( check( PATTERNS.getProperty( HELVETICA ), testString2 ) );
    }

    private String choseFont( String testText ) {
        for (String ttf : Arrays.asList( HELVETICA, DEJAVUSANS ) ) {
            if ( !check( PATTERNS.getProperty( ttf ), testText ) ) {
                return ttf;
            }
        }
        return null;
    }

    @Test
    void testChoseFont() {
        Assertions.assertEquals( DEJAVUSANS, choseFont( TEST_STRING_1 ) );
        Assertions.assertEquals( HELVETICA, choseFont( TEST_STRING_2 ) );
    }

}
