package org.fugerit.java.code.samples.tiff;

import com.twelvemonkeys.imageio.metadata.tiff.TIFFEntry;
import com.twelvemonkeys.imageio.plugins.tiff.TIFFImageMetadata;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageInputStream;
import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NamedNodeMap;

@Slf4j
public class TiffMetadataValidator {

    public static boolean validateTiffAndMetadata(File file, int... idTags) {
        ImageReader reader = null;
        try (ImageInputStream iis = ImageIO.createImageInputStream(file)) {
            Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);
            if (!readers.hasNext()) {
                log.warn("No ImageReader available for {}", file);
                return Boolean.TRUE;
            }

            reader = readers.next();
            reader.setInput(iis, true);

            IIOMetadata meta = reader.getImageMetadata(0);

            // TIFF plugin metadata format name
            String[] formatNames = meta.getMetadataFormatNames();
            for (String format : formatNames) {
                log.info("Metadata format: {}", format);
                Node root = meta.getAsTree(format);
                dumpNode(root, 0);
            }

            if (meta instanceof TIFFImageMetadata) {
                for ( int idTag : idTags ) {
                    TIFFImageMetadata tiffMeta = (TIFFImageMetadata) meta;
                    TIFFEntry entry = (TIFFEntry) tiffMeta.getTIFFField(idTag);
                    if (entry == null) {
                        return Boolean.FALSE;
                    }
                }
            } else {
                return Boolean.FALSE;
            }

            return Boolean.TRUE;

        } catch (Exception e) {
            log.error("Error reading TIFF metadata from {}: {}", file, e.getMessage(), e);
            return Boolean.FALSE;
        } finally {
            if (reader != null) {
                reader.dispose();
            }
        }
    }

    private static void dumpNode(Node node, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append("  ");
        }
        String indent = sb.toString();
        log.info("{}Node: {}", indent, node.getNodeName());

        NamedNodeMap attributes = node.getAttributes();
        if (attributes != null) {
            for (int i = 0; i < attributes.getLength(); i++) {
                Node attr = attributes.item(i);
                log.info("{}  @{} = {}", indent, attr.getNodeName(), attr.getNodeValue());
            }
        }

        Node child = node.getFirstChild();
        while (child != null) {
            dumpNode(child, level + 1);
            child = child.getNextSibling();
        }
    }

}
