package test.org.fugerit.java.codesamples;

import com.lowagie.text.Image;
import lombok.extern.slf4j.Slf4j;
import org.fugerit.java.codesamples.openpdf.LoadImage;
import org.fugerit.java.core.io.StreamIO;
import org.fugerit.java.core.lang.helpers.ClassHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
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
                Image img = LoadImage.fromBytes( StreamIO.readBytes( is ) );
                Assertions.assertNotNull( img );
            } catch (Exception e) {
                log.warn( "Errore "+e, e );
            }
        }
    }

    @Test
    void testImageIO() {
        for ( String current : IMAGES ) {
            String currentTest = "test_image/"+current;
            try (InputStream is = ClassHelper.loadFromDefaultClassLoader( currentTest ) ) {
                log.info( "currentTest ImageLoader.getPngImage() {}", currentTest );
                BufferedImage bufferedImage = ImageIO.read(is);
                Image img =  Image.getInstance(bufferedImage, null, false);
                Assertions.assertNotNull( img );
            } catch (Exception e) {
                log.warn( "Errore ImageLoader.getPngImage() "+e, e );
            }
        }
    }

}
