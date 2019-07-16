package annonation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)//此注解保留到程序运行阶段
public @interface TestAnnotation {

    public int id() default -1;

    public String msg();

}
