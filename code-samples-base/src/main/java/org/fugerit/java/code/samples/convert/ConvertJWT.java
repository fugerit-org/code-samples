package org.fugerit.java.code.samples.convert;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fugerit.java.core.function.SafeFunction;

import java.util.Base64;
import java.util.LinkedHashMap;

public class ConvertJWT implements  ConvertUtil {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public String load(String input) {
        return SafeFunction.get( () -> {
            LinkedHashMap<String, Object> map = this.mapper.readValue(input, LinkedHashMap.class);
            Base64.Encoder encoder = Base64.getUrlEncoder();
            StringBuilder res = new StringBuilder();
            res.append( encoder.encodeToString( mapper.writeValueAsString( map.get( "h" ) ).getBytes() ).replace( "=", "" ) );
            res.append( "." );
            res.append( encoder.encodeToString( mapper.writeValueAsString( map.get( "p" ) ).getBytes() ).replace( "=", "" ) );
            res.append( map.get( "c" ) );
            return res.toString();
        } );
    }

    @Override
    public String store(String input) {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        StringBuilder res = new StringBuilder();
        String[] chunks = input.split("\\.");
        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));
        res.append( "{\"h\":" );
        res.append( header );
        res.append( ",\"p\":" );
        res.append( payload );
        res.append( ",\"c\":\"" );
        for ( int k=2; k<chunks.length; k++ ) {
            res.append( "." );
            res.append( chunks[k] );
        }
        res.append( "\"}" );
        return res.toString();
    }

}