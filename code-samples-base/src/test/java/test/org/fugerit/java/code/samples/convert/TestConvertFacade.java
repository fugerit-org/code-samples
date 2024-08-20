package test.org.fugerit.java.code.samples.convert;

import lombok.extern.slf4j.Slf4j;
import org.fugerit.java.code.samples.convert.ConvertFacade;
import org.fugerit.java.code.samples.convert.ConvertJWT;
import org.fugerit.java.code.samples.convert.DeflateJWT;
import org.fugerit.java.code.samples.convert.PassThroughConvertUtil;
import org.fugerit.java.code.samples.deflate.DeflateGzipUtil;
import org.fugerit.java.code.samples.deflate.DeflateUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
class TestConvertFacade {

    /*
     * Example jwt from : https://jwt.io/#debugger-io
     */
    private static final String TEXT = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

    private void testWorker( ConvertFacade facade ) {
        String original = TEXT;
        String data = facade.getUtil().store( original );
        BigDecimal gain = new BigDecimal( 100D-((double)data.length())/((double)original.length())*100D ).setScale( 2, RoundingMode.HALF_EVEN );
        log.info( " original lenfth : {}, store length : {}, gain : {}, Convert FQCN {}, data : {}", original.length(), data.length(), gain, facade.getUtil().getClass().getName(), data );
        String load = facade.getUtil().load( data );
        Assertions.assertEquals( original, load );
    }

    @Test
    void testConvert() {
        ConvertFacade facade = new ConvertFacade();
        // conversion with pass
        log.info( "*** 1) convert : pass through" );
        facade.setUtil( new PassThroughConvertUtil() );
        testWorker( facade );
        // conversion deflate
        log.info( "*** 2) convert : deflate" );
        facade.setUtil( new DeflateUtil() );
        testWorker( facade );
        // convert jwt
        log.info( "*** 3) convert : custom" );
        facade.setUtil( new ConvertJWT() );
        testWorker( facade );
        // convert / deflate jwt
        log.info( "*** 4) convert : composite deflate / custom" );
        facade.setUtil( new DeflateJWT() );
        testWorker( facade );
        // convert / deflate alt
        log.info( "*** 5) convert : composite deflate alt" );
        facade.setUtil( new DeflateGzipUtil() );
        testWorker( facade );
    }

}
