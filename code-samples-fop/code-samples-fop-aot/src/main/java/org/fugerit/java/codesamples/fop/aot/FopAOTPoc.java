package org.fugerit.java.codesamples.fop.aot;

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.xmlgraphics.util.MimeConstants;
import org.fugerit.java.core.function.SafeFunction;
import org.fugerit.java.core.lang.helpers.ClassHelper;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class FopAOTPoc {

    private FopAOTPoc() {}

    public static void generatePdfATest1(OutputStream os) {
        String xslFoPath = "test-xsl-fo/pdf-a-test-1.fo";
        generate( xslFoPath, os );
    }

    public static void generate(String xslFoPath, OutputStream os ) {
        SafeFunction.apply( () -> {
            FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
            FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
            foUserAgent.setAccessibility( true );
            foUserAgent.setKeepEmptyTags( true );
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, os);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            Result res = new SAXResult(fop.getDefaultHandler());
            try (InputStreamReader xslFoReader = new InputStreamReader(ClassHelper.loadFromDefaultClassLoader( xslFoPath )) ) {
                transformer.transform(new StreamSource(xslFoReader), res);
            }
        } );
    }

}
