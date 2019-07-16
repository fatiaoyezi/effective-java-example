### Java中Comparable和Comparator的使用与比较



在java中提供了两种排序方式。

首先，我们谈谈Comparable

该类位于java.lang下 ，通常认为，一个类如果继承了Comparable接口，就意味着“**该类支持排序**”。具体的的操作是，定义一个类，实现Comparable接口，重写compareTo方法，在方法中定义排序的规则。我们可以将Comparable看成一种内部比较器。

如果一个集合(或数组)汇总存放的是实现了Comparable接口类的对象，则该List列表(或数组)可以通过 Collections.sort（或 Arrays.sort）进行排序。



#### Comparable

jdk中关于Comparable接口的定义仅仅只包含一个抽象函数，其内容如下：

```java
package java.lang;
import java.util.*;
 
public interface Comparable<T> {
    public int compareTo(T o);
}
```

值得一说的是，假设我们通过 x.compareTo(y) 来“比较x和y的大小”。若返回“负数”，意味着“x比y小”；返回“零”，意味着“x等于y”；返回“正数”，意味着“x大于y”。 



那具体我们该怎么使用Comparable来对相同类型的数据进行排序呢？

首先声明一个Person实体类，并实现Comparator接口。

(这里用到了Lombok插件，不了解的木友可以先问下度娘)

```java
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
```

编写测试类：

```java
public class ComparableTest {

    public static void main(String[] args) {
	
        List<Person> persons = new ArrayList();

        persons.add(new Person("三笠",16));
        persons.add(new Person("团长",20));
        persons.add(new Person("兵长",18));

        System.out.println("根据年龄排序前:" + persons);
        Collections.sort(persons);
        System.out.println("根据年龄排序后:" + persons);
        
    }
}
```

输出结果：

```java
根据年龄排序前:[Person(name=三笠, age=16), Person(name=团长, age=20), Person(name=兵长, age=18)]
根据年龄排序后:[Person(name=三笠, age=16), Person(name=兵长, age=18), Person(name=团长, age=20)]
```



#### Comparator简介

Comparator位于java.util包下，当我们需要控制某个类的次序,而该类本身不支持排序(即没有实现Comparable接口);那么，我们可以新建一个该类的比较器来进行排序（多数情况下，我们都是使用的匿名内部类来实现）。而这个比较器需要实现Comparator接口，并重写compare方法。

例如，在使用第三方jar包，源码中并没有实现Comparable接口，这时候便需要使用Comparator接口

Comparator是一个专用的比较器，当这个对象不支持自比较或者自比较函数不能满足要求时，可写一个比较器来完成两个对象之间大小的比较。

个人认为Comparator要比Comparable复杂一点

首先我们先看看jdk中关于Comparator的定义吧

![1561537816131](C:\Users\yezishu\AppData\Local\Temp\1561537816131.png)



jdk1.8之前Comparator里面只有compare和equals两个方法。jdk1.8之后新增许多方法。

本篇文章，我们只关注compare方法。

实例如下：

假设Person类并没有实现Comparable接口，我们还可以这样来实现排序

```
public class ComparatorTest {

    public static void main(String[] args) {

        List<Person> persons = new ArrayList();

        persons.add(new Person("三笠",16));
        persons.add(new Person("胡艳羽",20));
        persons.add(new Person("兵长",18));
		//这里的sort方法多了一个参数，其实就是继承Comparator的一个匿名内部类，可称之为比较器。
        Collections.sort(persons, new Comparator<Person>() {
            public int compare(Person o1, Person o2) {
                //根据年龄比较
                return o1.getAge() -o2.getAge();
            }
        });
    }
}
```





差不多就这么多了，那我们应该记住什么呢？

当我们在java中需要进行排序的时候的，应当联想到Comparable和Comparator这两个接口。并能认清他们的区别。

其实从示例代码中，我们能看到。当使用Comparable的前提是，用于比较的数据类型必须实现Comparble接口，并重写compareTo方法，在其中定义比较规则。可形象的将其记为一种内部比较器。

而使用Comparator时，用于比较的数据类型，并不需要实现其接口。而是在其外部自定义一个比较器，可将其记为一种外部比较器。

对于排序而言，其实它们的目的都一样，只是实现方式不同。

 