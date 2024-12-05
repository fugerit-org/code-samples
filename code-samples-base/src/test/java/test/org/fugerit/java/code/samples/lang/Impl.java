package test.org.fugerit.java.code.samples.lang;

public class Impl implements Interface1, Interface2 {

    @Override
    public int method1() {
        return 1;
    }

    @Override
    public int method2() {
        return 2;
    }

    @Override
    public String test() {
        return Interface1.super.test();
    }

}
