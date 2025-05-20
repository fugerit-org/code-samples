package org.fugerit.java.codesamples.fop.aot;

import org.fugerit.java.core.function.SafeFunction;

import java.io.File;
import java.io.FileOutputStream;

public class MainFopAOT {

    public static void main(String[] args) {
        String filePath = args[0];
        SafeFunction.apply(() -> {
            File outputFile = new File( filePath );
            try (FileOutputStream fos = new FileOutputStream( outputFile )) {
                FopAOTPoc.generatePdfATest1( fos );
            }
        });
    }

}
