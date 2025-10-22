package org.fugerit.java.code.samples.omit.model;

public class Level1Model {

    private String property1;

    private String property2;

    public String getProperty1() {
        return property1;
    }

    public void setProperty1(String property1) {
        this.property1 = property1;
    }

    public String getProperty2() {
        return property2;
    }

    public void setProperty2(String property2) {
        this.property2 = property2;
    }

    private Level2Model level2Model;

    public Level2Model getLevel2Model() {
        return level2Model;
    }

    public void setLevel2Model(Level2Model level2Model) {
        this.level2Model = level2Model;
    }

}
