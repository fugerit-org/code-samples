package test.testorg.fugerit.java.codesamplesfjdoc;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Freemarker template syntax verify example
 *
 * It will check all templates in folder :
 * 'src/main/resources/code-samples-fj-doc/template'
 * for syntax errors.
 *
 * Expected templates with syntac errors :
 * macro-fail.ftl
 * template-fail.ftl
 *
 */
@Slf4j
class VerifyTemplateTest {

    /*
     * This method will :
     * - creates a FreeMarker configuration pointing at the template folders
     * - iterates over templates
     * - return a list of templates with syntac error
     */
    private Collection<String> verifyTemplateSyntaxErrors() throws IOException {
        List<String> errors = new ArrayList<>();
        File baseFolder = new File( "src/main/resources/code-samples-fj-doc/template/" );
        Configuration cfg = new Configuration( Configuration.VERSION_2_3_33 );
        cfg.setDirectoryForTemplateLoading( baseFolder );
        this.verifyTemplateSyntaxErrorsIterate( baseFolder, errors, cfg, baseFolder );
        return errors;
    }

    private void verifyTemplateSyntaxErrorsIterate(File baseFolder, List<String> errors, Configuration cfg, File currentFolder ) throws IOException {
        log.info( "base folder : {},  current folder: {}", baseFolder, currentFolder );
        for ( String template : currentFolder.list() ) {
            File templateFile = new File( currentFolder, template );
            if ( templateFile.isDirectory() ) {
                this.verifyTemplateSyntaxErrorsIterate( currentFolder, errors, cfg, templateFile );
            } else {
                String templatePath = templateFile.getCanonicalPath().substring( baseFolder.getCanonicalPath().length()+1 );
                try {
                    Template t = cfg.getTemplate( templatePath );
                    log.info( "Template syntax OK: {} - template : {}", templatePath, t.getName() );
                } catch (ParseException e) {
                    log.error( String.format( "Template syntax KO: %s", templatePath ), e );
                    errors.add( templatePath );
                }
            }
        }
    }

    @Test
    void test() throws IOException {
        Collection<String> errors = verifyTemplateSyntaxErrors();
        log.info( "templates with errors: {}", errors );
        Assertions.assertEquals( 2, errors.size() );
    }

}
