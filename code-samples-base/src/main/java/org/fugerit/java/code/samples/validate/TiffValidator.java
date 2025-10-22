package org.fugerit.java.code.samples.validate;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.util.Iterator;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

public class TiffValidator {

    /**
     * Validate if a file is a valid TIFF image.
     *
     * @param file TIFF file
     * @return true if valid TIFF, false otherwise
     */
    public static int isValidTiff(File file) {
        try (ImageInputStream iis = ImageIO.createImageInputStream(file)) {
            if (iis == null) {
                return -1;
            }

            Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);
            if (!readers.hasNext()) {
                return -2;
            }

            ImageReader reader = readers.next();
            String formatName = reader.getFormatName().toLowerCase();

            System.out.println( "formatName : " + formatName );

            if (!formatName.contains("tiff") && !formatName.contains("tif")) {
                return -3;
            }

            // Try reading basic image info
            reader.setInput(iis, true);
            int width = reader.getWidth(0);
            int height = reader.getHeight(0);

            System.out.println( width + " x " + height );

            if (width > 0 && height > 0) {
                return 0;
            } else {
                return -4;
            }

        } catch (Exception e) {
            return 1;
        }
    }

    /**
     * Extract and print TIFF metadata.
     *
     * @param file TIFF file
     */
    public static void printTiffMetadata(File file) {
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(file);

            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {
                    System.out.println(directory.getName() + " - " + tag.getTagName() + " = " + tag.getDescription());
                }
            }
        } catch (Exception e) {
            System.err.println("Failed to read metadata: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("Usage: java TiffValidator <tiff-file>");
            return;
        }

        File file = new File(args[0]);

        System.out.println("Checking TIFF file: " + file.getAbsolutePath());

        int res = isValidTiff(file);
        System.out.println("Valid: " + res);
        if (res == 0) {
            System.out.println("Valid TIFF file.");
            System.out.println("Metadata:");
            printTiffMetadata(file);
        } else {
            System.out.println("Invalid TIFF file.");
        }
    }
}
