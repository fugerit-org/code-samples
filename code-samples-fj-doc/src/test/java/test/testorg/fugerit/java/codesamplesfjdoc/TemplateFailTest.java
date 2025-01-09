package test.testorg.fugerit.java.codesamplesfjdoc;

import lombok.extern.slf4j.Slf4j;
import org.fugerit.java.codesamplesfjdoc.DocHelper;
import org.fugerit.java.core.cfg.ConfigRuntimeException;
import org.fugerit.java.doc.base.config.DocConfig;
import org.fugerit.java.doc.base.process.DocProcessContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * This is a basic example of Fugerit Venus Doc usage,
 * running this main the program will :
 * - creates data to be used in document model
 * - renders the 'document.ftl' template
 * - print the result in markdown format on the log
 *
 * For further documentation :
 * https://github.com/fugerit-org/fj-doc
 */
@Slf4j
class TemplateFailTest {

    private int generate() throws IOException {
        try ( ByteArrayOutputStream baos = new ByteArrayOutputStream() ) {
            // creates the doc helper
            DocHelper docHelper = new DocHelper();
            // create custom data for the fremarker template 'template-fail.ftl'
            Properties params = new Properties();
            params.setProperty( "prop1", "The king of Rivendell is {0}" );
            // handler id
            String handlerId = DocConfig.TYPE_MD;
            // output generation
            docHelper.getDocProcessConfig().fullProcess( "template-fail", DocProcessContext.newContext( "params", params ), handlerId, baos );
            // print the output
            log.info( "html output : \n{}", new String( baos.toByteArray(), StandardCharsets.UTF_8 ) );
            return baos.size();
        }
    }


    @Test
    void test() {
        Assertions.assertThrows( ConfigRuntimeException.class, this::generate);
    }

    /*
     * Class used to wrap data to be rendered in the document template
     */
    public static class People {

        private String name;

        private String surname;

        private String title;

        public People(String name, String surname, String title) {
            this.name = name;
            this.surname = surname;
            this.title = title;
        }

        public String getName() {
            return name;
        }

        public String getSurname() {
            return surname;
        }

        public String getTitle() {
            return title;
        }
    }

}
