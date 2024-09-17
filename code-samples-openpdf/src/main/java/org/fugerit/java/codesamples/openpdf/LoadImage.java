package org.fugerit.java.codesamples.openpdf;

import com.lowagie.text.Element;
import com.lowagie.text.Image;
import org.fugerit.java.core.function.SafeFunction;

public class LoadImage {

    private LoadImage() {}

    public static Image fromBytes( byte[] data ) {
        return SafeFunction.get( () -> {
            Image image = Image.getInstance( data );
            image.scaleAbsoluteHeight(70);
            image.scaleAbsoluteWidth(70);
            image.setAlignment(Element.ALIGN_CENTER);
            return image;
        } );
    }

}