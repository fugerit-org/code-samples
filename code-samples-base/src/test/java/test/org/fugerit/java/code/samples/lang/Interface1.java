package test.org.fugerit.java.code.samples.lang;

public interface Interface1 {

    int method1();

    default String test() {
        return Interface1.class.getSimpleName();
    }

}
