package chapter1_object;

/**
 * @author fatiaoyezi
 * @title:
 * @projectName effective-java-example
 * @description: 通过私有构造器强化不可实例化的能力-----第四条
 * @date 2019/7/16 15:15
 */
public class Utils {

    private Utils(){
        throw new AssertionError("Utils cant not have instance");
    }

}
