package org.fugerit.java.code.samples.tiff;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.File;
import java.util.Iterator;

@Slf4j
public class TiffValidator {

    private TiffValidator() {}

    public static boolean isValidTiff(File file) {
        try (ImageInputStream iis = ImageIO.createImageInputStream(file)) {
            if (iis == null) {
                return false;
            }

            Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);
            if (!readers.hasNext()) {
                return false; // no reader for this format
            }

            ImageReader reader = readers.next();
            reader.setInput(iis, true);
            reader.read(0);  // try reading first page/frame
            reader.dispose();

            return Boolean.TRUE;
        } catch (Exception e) {
            log.error( e.getMessage(), e );
            return Boolean.FALSE;
        }
    }

}
