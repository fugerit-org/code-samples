package org.fugerit.java.code.samples.tiff;

import com.twelvemonkeys.imageio.metadata.exif.TIFF;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

@Slf4j
public class TiffMetadataValidatorTest {

    @Test
    void testValidTiffNotValid() {
        File sampleFile = new File("src/test/resources/sample/sample.tiff");
        boolean res = TiffMetadataValidator.validateTiffAndMetadata( sampleFile, TIFF.TAG_X_RESOLUTION );
        Assertions.assertFalse(res);
    }

    @Test
    void testValidTiffValid() {
        File sampleFile = new File("src/test/resources/sample/file_example_TIFF_1MB.tiff");
        boolean res = TiffMetadataValidator.validateTiffAndMetadata( sampleFile, TIFF.TAG_X_RESOLUTION );
        Assertions.assertTrue(res);
    }

}
