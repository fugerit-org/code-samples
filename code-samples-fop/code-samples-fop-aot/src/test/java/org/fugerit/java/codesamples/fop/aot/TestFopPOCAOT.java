package org.fugerit.java.codesamples.fop.aot;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

@Slf4j
class TestFopPOCAOT {

    @Test
    void testFopMain() {
        File file = new File("target/pdf-a-test-1-fop-aot-main.pdf");
        String filePath = file.getAbsolutePath();
        MainFopAOT.main( new String[] { filePath } );
        Assertions.assertTrue( file.exists() ) ;
    }

}
