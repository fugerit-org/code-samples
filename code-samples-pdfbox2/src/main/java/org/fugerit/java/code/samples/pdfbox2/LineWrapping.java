package org.fugerit.java.code.samples.pdfbox2;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Line wrapping test.
 *
 * This code will wrap text on more lines if it is longer than maximum page width.
 */
public class LineWrapping {

    private void handleLine( String l, List<String> list, int maxSize ) {
        String[] splits = l.split( " " );
        StringBuilder builder = new StringBuilder();
        for ( int i=0; i<splits.length; i++ ) {
            String s = splits[i];
            if ( builder.length() + s.length() > maxSize ) {
                list.add( builder.toString() );
                builder = new StringBuilder();
            }
            builder.append( s );
            if ( i != splits.length - 1 ) {
                builder.append( " " );
            }
        }
        list.add( builder.toString() );
    }

    private List<String> splitLines( String[] lines, int maxSize ) {
        List<String> list = new ArrayList<>();
        for ( String l : lines ) {
            if ( l.length() > maxSize ) {
                this.handleLine( l, list, maxSize );
            } else {
                list.add( l );
            }
        }
        return list;
    }

    public void generatePDF(OutputStream os, PDFont font, String[] lines) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage currentPage = new PDPage();
            PDPageContentStream cs = new PDPageContentStream( document, currentPage );
            float fontSize = 14;
            float leading = 12;
            cs.setFont( font, fontSize );
            cs.setLeading( leading );
            float baseX = 25;
            float baseY = 750;
            float offsetY = 0;
            float maxPageWidth = 1400;
            int maxLineLength = (int)(maxPageWidth/fontSize);  // actual formula can be different depending on font
            List<String> list = splitLines( lines, maxLineLength );
            for ( String s : list ) {
                cs.beginText();
                cs.newLineAtOffset( baseX, baseY+offsetY );
                cs.showText( s );
                cs.endText();
                offsetY-= fontSize;
            }
            cs.stroke();
            cs.close();
            document.addPage( currentPage );
            document.save( os );
        }
    }

}
