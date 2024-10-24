package org.fugerit.java.codesamples.fop.common;

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.xmlgraphics.io.ResourceResolver;
import org.apache.xmlgraphics.util.MimeConstants;
import org.fugerit.java.core.function.SafeFunction;
import org.fugerit.java.core.lang.helpers.ClassHelper;
import org.fugerit.java.doc.mod.fop.PdfFopTypeHandler;
import org.fugerit.java.doc.mod.fop.config.FopConfigClassLoaderWrapper;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class FopPOC {

    private FopPOC() {}

    public static void generatePdfATest1(OutputStream os) {
        String xslFoPath = "test-xsl-fo/pdf-a-test-1.fo";
        String fopConfigPath = "fop-config/fop-config-pdf-a-ua.xml";
        generate( xslFoPath, fopConfigPath, os );
    }

    public static void generate(String xslFoPath, String fopConfigPath, OutputStream os ) {
        SafeFunction.apply( () -> {
            ResourceResolver customResourceResolver = (ResourceResolver) ClassHelper.newInstance(PdfFopTypeHandler.ATT_FOP_CONFIG_RESOLVER_TYPE_DEFAULT );
            FopConfigClassLoaderWrapper fopConfig = new FopConfigClassLoaderWrapper(fopConfigPath, customResourceResolver);
            FopFactory fopFactory = fopConfig.newFactory();
            FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
            foUserAgent.setAccessibility( true );
            foUserAgent.setKeepEmptyTags( true );
            Fop fop = fopConfig.newFactory().newFop(MimeConstants.MIME_PDF,foUserAgent,os);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            Result res = new SAXResult(fop.getDefaultHandler());
            try (InputStreamReader xslFoReader = new InputStreamReader(ClassHelper.loadFromDefaultClassLoader( xslFoPath )) ) {
                transformer.transform(new StreamSource(xslFoReader), res);
            }

        } );
    }

}
