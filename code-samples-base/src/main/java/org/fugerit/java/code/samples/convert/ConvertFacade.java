package org.fugerit.java.code.samples.convert;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class ConvertFacade {

    @Getter @Setter
    private ConvertUtil util;

    public ConvertFacade() {
            this( new ConvertUtil() {} );
    }

    public String load(String input) {
        return util.load(input);
    }

    public String store(String input) {
        return util.store(input);
    }

}
