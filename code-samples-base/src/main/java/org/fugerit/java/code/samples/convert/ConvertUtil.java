package org.fugerit.java.code.samples.convert;

/**
 * Conversion interface, default implementation is to return input both on load() and store()
 */
public interface ConvertUtil {

    default String store( String input ) { return input; }

    default String load( String input ) { return input; }

}
