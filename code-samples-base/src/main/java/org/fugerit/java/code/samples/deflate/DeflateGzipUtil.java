package org.fugerit.java.code.samples.deflate;

import lombok.extern.slf4j.Slf4j;
import org.fugerit.java.code.samples.convert.ConvertUtil;
import org.fugerit.java.core.function.SafeFunction;
import org.fugerit.java.core.io.StreamIO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

@Slf4j
public class DeflateGzipUtil implements ConvertUtil {

    @Override
    public String load( String input ) {
        return this.decompress( Base64.getUrlDecoder().decode( input ) );
    }

    public String decompress( byte[] input ) {
        return SafeFunction.get( () -> {
            try (ByteArrayInputStream bis = new ByteArrayInputStream( input );
                 GZIPInputStream gis = new GZIPInputStream( bis ) ) {
                byte[] data = StreamIO.readBytes( gis );
                return new String( data, StandardCharsets.UTF_8 );
            }
        } );
    }

    @Override
    public String store( String input ) {
        return Base64.getUrlEncoder().encodeToString( this.compress( input ) );
    }

    public byte[] compress( String input ) {
        return SafeFunction.get( () -> {
            try (ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
                try (GZIPOutputStream gzip = new GZIPOutputStream( buffer ) ) {
                    gzip.write( input.getBytes( StandardCharsets.UTF_8 ) );
                }
                return buffer.toByteArray();
            }
        } );
    }

}
