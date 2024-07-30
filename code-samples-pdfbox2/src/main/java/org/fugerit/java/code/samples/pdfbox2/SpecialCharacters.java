package org.fugerit.java.code.samples.pdfbox2;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import java.io.*;

/**
 * Handling of special characters
 *
 * In this example we have : U+2002 ('enspace')
 *
 * See the Unit test for usage
 */
public class SpecialCharacters {

    private void generatePDFWorker(OutputStream os, PDDocument document, PDFont font, String line) throws IOException {
        PDPage currentPage = new PDPage();
        PDPageContentStream cs = new PDPageContentStream( document, currentPage );
        cs.setFont( font, 14 );
        cs.setLeading(12);
        cs.beginText();
        cs.newLineAtOffset(25, 550);
        cs.showText( line );
        cs.endText();
        cs.stroke();
        cs.close();
        document.addPage( currentPage );
        document.save( os );
    }

    public void generatePDF(OutputStream os, InputStream fontIS, String line) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDType0Font font = PDType0Font.load( document, fontIS );
            this.generatePDFWorker( os, document, font, line );
        }
    }

    public void generatePDF(OutputStream os, PDFont font, String line) throws IOException {
        try (PDDocument document = new PDDocument()) {
           this.generatePDFWorker( os, document, font, line );
        }
    }

}
