package org.fugerit.java.codesamplesfjdoc;

import org.fugerit.java.doc.freemarker.process.FreemarkerDocProcessConfig;
import org.fugerit.java.doc.freemarker.process.FreemarkerDocProcessConfigFacade;

/**
 * DocHelper, version : auto generated on 2024-08-23 22:54:48.183
 */
public class DocHelper {

     private FreemarkerDocProcessConfig docProcessConfig = FreemarkerDocProcessConfigFacade.loadConfigSafe( "cl://code-samples-fj-doc/fm-doc-process-config.xml" );

     public FreemarkerDocProcessConfig getDocProcessConfig() { return this.docProcessConfig; }

}
