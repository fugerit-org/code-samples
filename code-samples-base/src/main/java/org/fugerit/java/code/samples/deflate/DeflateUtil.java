package org.fugerit.java.code.samples.deflate;

import lombok.extern.slf4j.Slf4j;
import org.fugerit.java.code.samples.convert.ConvertUtil;
import org.fugerit.java.core.function.SafeFunction;
import org.fugerit.java.core.io.StreamIO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.zip.Deflater;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.Inflater;

@Slf4j
public class DeflateUtil implements ConvertUtil {

    @Override
    public String load( String input ) {
        return this.decompress( Base64.getUrlDecoder().decode( input ) );
    }

    public String decompress( byte[] input ) {
        return SafeFunction.get( () -> {
            Inflater inflater = new Inflater();
            byte[] output = new byte[8192];
            inflater.setInput( input );
            int resultLength = inflater.inflate( output );
            inflater.end();
            return new String(output, 0, resultLength, StandardCharsets.UTF_8);
        } );
    }

    @Override
    public String store( String input ) {
        return Base64.getUrlEncoder().encodeToString( this.compress( input ) );
    }

    public byte[] compress( String input ) {
        return SafeFunction.get( () -> {
            // Compress the bytes
            byte[] output = new byte[8192];
            Deflater deflater = new Deflater();
            deflater.setInput( input.getBytes( StandardCharsets.UTF_8 ) );
            deflater.finish();
            int compressedDataLength = deflater.deflate(output);
            deflater.end();
            log.info( "original size : {}, compressed size : {}", input.length(), compressedDataLength );
            return Arrays.copyOf( output, compressedDataLength );
        } );
    }

}
