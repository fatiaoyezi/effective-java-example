package sort.test;

import sort.pojo.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComparableTest {

    public static void main(String[] args) {

        List<Person> persons = new ArrayList();

        persons.add(new Person("三笠",16));
        persons.add(new Person("胡艳羽",20));
        persons.add(new Person("兵长",18));

        System.out.println("根据年龄排序前:" + persons);
        Collections.sort(persons);
        System.out.println("根据年龄排序后:" + persons);

    }
}
