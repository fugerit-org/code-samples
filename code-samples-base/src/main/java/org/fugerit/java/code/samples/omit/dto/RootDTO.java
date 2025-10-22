package org.fugerit.java.code.samples.omit.dto;

import org.fugerit.java.code.samples.omit.OmittedException;
import org.fugerit.java.code.samples.omit.model.Level1Model;
import org.fugerit.java.code.samples.omit.model.RootModel;

public class RootDTO extends RootModel {

    @Override
    public String getRootField2() {
        if ( Boolean.TRUE ) {
            throw new OmittedException( "getRootField2" );
        }
        return super.getRootField2();
    }

}
