package org.fugerit.java.code.samples.omit.dto;

import org.fugerit.java.code.samples.omit.OmittedException;
import org.fugerit.java.code.samples.omit.model.Level1Model;

public class Level1DTO extends Level1Model {

    @Override
    public void setProperty1(String property1) {
        if ( Boolean.TRUE ) {
            throw new OmittedException( "setProperty1" );
        }
        super.setProperty1(property1);
    }

}
