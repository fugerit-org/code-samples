package test.testorg.fugerit.java.codesamplesfjdoc;

import org.fugerit.java.codesamplesfjdoc.DocHelper;
import org.fugerit.java.doc.base.config.DocConfig;
import org.fugerit.java.doc.base.process.DocProcessContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

class TestEndlineFop {

    @Test
    void test() throws IOException {
        File outputFile = new File( "target/endline-fop.pdf" );
        try ( FileOutputStream fos = new FileOutputStream( outputFile ) ) {
            DocHelper docHelper = new DocHelper();
            // create custom data for the fremarker template 'document.ftl'
            List<DocHelperTest.People> listPeople = Arrays.asList( new DocHelperTest.People( "Luthien", "Tinuviel", "Queen" ), new DocHelperTest.People( "Thorin", "Oakshield", "King" ) );
            // handler id
            String handlerId = DocConfig.TYPE_PDF;
            // output generation
            docHelper.getDocProcessConfig().fullProcess( "endline-fop", DocProcessContext.newContext(), handlerId, fos );
            Assertions.assertTrue( outputFile.exists() );
        }
    }

}
