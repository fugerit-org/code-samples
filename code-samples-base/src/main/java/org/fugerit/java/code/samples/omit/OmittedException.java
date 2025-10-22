package org.fugerit.java.code.samples.omit;

public class OmittedException extends RuntimeException {

    public OmittedException(String field) {
        super( String.format( "Field %s is omitted", field ) );
    }
}
