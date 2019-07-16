package annonation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

@TestAnnotation(msg = "error")
public class Test {

    @Check("hi")
    int a;

    @SuppressWarnings("deprecation")
    public void test1(){

    }



    public static void main(String[] args) {

        boolean hasAnnotation = Test.class.isAnnotationPresent(TestAnnotation.class);

        if (hasAnnotation) {
            TestAnnotation annotation = Test.class.getAnnotation(TestAnnotation.class);

            System.out.println("id:" + annotation.id());
            System.out.println("msg:" + annotation.msg());
        }

        try {
            Field a = Test.class.getDeclaredField("a");
            a.setAccessible(true);
            //获取一个成员变量上的注解
            Check check = a.getAnnotation(Check.class);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }


    }


}
