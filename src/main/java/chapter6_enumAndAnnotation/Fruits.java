package chapter6_enumAndAnnotation;


/**
 * @author fatiaoyezi
 * @title:
 * @projectName effective-java-example
 * @description: 用实例域代替序数----第31条
 * @date 2019/7/12 14:55
 */
public enum Fruits{



    APPLE(1),
    PEAR(2),
    ORANGE(3);

    private final int numberOfFruits;

    Fruits(int numberOfFruits) {
        this.numberOfFruits = numberOfFruits;
    }


    public static void main(String[] args) {
        Enum apple = Fruits.valueOf(Fruits.class, "APPLE");
        System.out.println(apple.ordinal());
    }


}

