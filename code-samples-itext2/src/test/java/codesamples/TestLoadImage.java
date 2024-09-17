package codesamples;

import com.lowagie.text.Image;
import lombok.extern.slf4j.Slf4j;
import org.fugerit.java.codesamples.itext2.LoadImage;
import org.fugerit.java.core.io.StreamIO;
import org.fugerit.java.core.lang.helpers.ClassHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

@Slf4j
class TestLoadImage {

    private static final String[] IMAGES = { "logo1.png", "logo2.png" };

    @Test
    void testFromBytes() {
        for ( String current : IMAGES ) {
            String currentTest = "test_image/"+current;
            try (InputStream is = ClassHelper.loadFromDefaultClassLoader( currentTest ) ) {
                log.info( "currentTest {} - {}", LoadImage.class.getName(), currentTest );
                Image img = LoadImage.fromBytes(StreamIO.readBytes( is ) );
                Assertions.assertNotNull( img );
            } catch (Exception e) {
                log.warn( "Errore "+e, e );
            }
        }
    }

}