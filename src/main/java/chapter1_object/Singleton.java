package chapter1_object;

import java.io.Serializable;

/**
 * @author fatiaoyezi
 * @title:
 * @projectName effective-java-example
 * @description: 用私有构造器或者枚举类型强化Singleton属性----第三条
 * @date 2019/7/16 14:37
 */
public class Singleton implements Serializable {

    private static final Singleton INSTANCE = new Singleton();

    //构造器私有
    private Singleton(){}

    public static Singleton getInstance(){
        return INSTANCE;
    }

    private Object readResolve() {
        return INSTANCE;
    }

    enum SingletonByEnum{
        INSTANCE;
    }
}
