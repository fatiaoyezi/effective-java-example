package sort.test;

import sort.pojo.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorTest {

    public static void main(String[] args) {

        List<Person> persons = new ArrayList();

        persons.add(new Person("三笠",16));
        persons.add(new Person("胡艳羽",20));
        persons.add(new Person("兵长",18));

        Collections.sort(persons, new Comparator<Person>() {
            public int compare(Person o1, Person o2) {
                //根据年龄比较
                return o1.getAge() -o2.getAge();
            }
        });
    }
}
