package sort.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person implements Comparable {

    private String name;
    private int age;

    public int compareTo(Object o) {
        Person s = (Person) (o);
        //根据年龄进行比较
        return this.age - s.age;
    }
}
