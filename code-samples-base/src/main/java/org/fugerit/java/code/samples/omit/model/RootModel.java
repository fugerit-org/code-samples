package org.fugerit.java.code.samples.omit.model;

import java.util.List;

public class RootModel {

    private String rootField1;

    private String rootField2;

    private String rootField3;

    private Level1Model level1;

    private List<Level1Model> level1List;

    public String getRootField1() {
        return rootField1;
    }

    public void setRootField1(String rootField1) {
        this.rootField1 = rootField1;
    }

    public String getRootField2() {
        return rootField2;
    }

    public void setRootField2(String rootField2) {
        this.rootField2 = rootField2;
    }

    public String getRootField3() {
        return rootField3;
    }

    public void setRootField3(String rootField3) {
        this.rootField3 = rootField3;
    }

    public Level1Model getLevel1() {
        return level1;
    }

    public void setLevel1(Level1Model level1) {
        this.level1 = level1;
    }

    public List<Level1Model> getLevel1List() {
        return level1List;
    }

    public void setLevel1List(List<Level1Model> level1List) {
        this.level1List = level1List;
    }

}
