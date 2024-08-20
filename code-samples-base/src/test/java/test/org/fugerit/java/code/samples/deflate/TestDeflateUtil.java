package test.org.fugerit.java.code.samples.deflate;

import lombok.extern.slf4j.Slf4j;
import org.fugerit.java.code.samples.deflate.DeflateUtil;
import org.fugerit.java.core.function.SafeFunction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Slf4j
class TestDeflateUtil {

    /*
     * Example jwt from : https://jwt.io/#debugger-io
     */
    private static final String TEXT = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

    @Test
    void testDeflate() {
        DeflateUtil util = new DeflateUtil();
        String data = util.store( TEXT );
        log.info( "original length : {}, new length {}, data : {}", TEXT.length(), data.length(), data );
        String test = util.load( data );
        Assertions.assertEquals( TEXT, test );
    }

    @Test
    void testDeflate2() {
        DeflateUtil util = new DeflateUtil();
        byte[] data = util.compress( TEXT );
        log.info( "original length : {}, new length {}", TEXT.length(), data.length );
        String test = util.decompress( data );
        Assertions.assertEquals( TEXT, test );
    }

    @Test
    void testDeflate3() {
        SafeFunction.apply( () -> {
            // Compress the bytes
            byte[] output = new byte[1024];
            Deflater deflater = new Deflater();
            deflater.setInput( TEXT.getBytes() );
            deflater.finish();
            int compressedDataLength = deflater.deflate(output);
            deflater.end();

            log.info("Compressed Message length : {}", compressedDataLength);

            // Decompress the bytes
            Inflater inflater = new Inflater();
            inflater.setInput(output, 0, compressedDataLength);
            byte[] result = new byte[1024];
            int resultLength = inflater.inflate(result);
            inflater.end();

            log.info("Result Message length : {}", resultLength);

            String decompressedText = new String( result, 0 , resultLength );
            Assertions.assertEquals( TEXT, decompressedText );

        } );

    }

}
