package test.org.fugerit.java.code.samples.lang;

public interface Interface2 {

    int method2();

    default String test() {
        return Interface2.class.getSimpleName();
    }

}
