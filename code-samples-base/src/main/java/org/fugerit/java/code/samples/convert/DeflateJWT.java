package org.fugerit.java.code.samples.convert;

import org.fugerit.java.code.samples.deflate.DeflateUtil;

public class DeflateJWT implements ConvertUtil {

    private ConvertUtil deflate;

    private ConvertUtil jwt;

    public DeflateJWT() {
        this.deflate = new DeflateUtil();
        this.jwt = new ConvertJWT();
    }

    @Override
    public String store(String input) {
        return this.deflate.store( this.jwt.store( input ) );
    }

    @Override
    public String load(String input) {
        return this.jwt.load( this.deflate.load( input ) );
    }
}
