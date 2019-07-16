
  [Chapter1](#111)

<span id='charpter1'>#Chapter1：创建和销毁对象</span>

## 1.考虑用静态工厂方法代替构造器

在java中，创建一个类的实例，最常用的方法就是提供一个公有的构造器，然后通过new关键字得到。
还有一种方法是在类中提供一个此类实例的静态工厂方法( static factory method)。

以jdk中自带的Boolean类(boolean的包装类)为例，它的静态工厂方法如下：

 ```java
 public static Boolean valueOf(boolean b) {
     return b ? Boolean.TRUE : Boolean.FALSE;
   }
```

此时，我们既可以通过Boolean类中的构造器获取实例，也可以通过valueof()静态方法获取实例：
```java
   //使用构造器
   Boolean b1 = new Boolean(true);
   //使用静态工厂方法
   Boolean b2 = Boolean.valueOf(true);
```
既然通过构造器就能获取类实例，那为什么还要多此一举通过静态工厂方法获取呢？





###### 静态工厂方法的优点：

<b>1. 见名知意</b>
  在Boolean类中有两个构造方法如下：
  ```java
      //传入一个boolean参数
      public Boolean(boolean value) { this.value = value;}
      //传入一个String参数
      public Boolean(String s) { this(toBoolean(s)); }
```
  如果只看构造器签名，完全无法得知两者的区别和使用场景；但是通过静态工厂方法，我们可以在方法名称中做手脚，更具具体的使用场景，取一个见名知意的名字。如：
  ```java
      //传入一个boolean参数
      public static byBoolean(boolean value) {this.value = value;}
      //传入一个String参数
      public static byString(String s) { this(toBoolean(s)); }
  ```
<b>2. 可复用性</b>
  当程序经常请求创建相同的对象时，并且创建成本较高，使用共静态工厂方法可以极大提高性能。
<b>3. </b>
<b>4. 创建泛型类实例的代码更为简洁</b>
  以jdk中的HashMap类为例(个人认为此条可忽略不计，jdk7及以后版本构造函数可用空的类型参数：
  ```java
      //使用构造器创建
      Map<String, List<String>> m1 = new HashMap<String, List<String>>();
      //使用静态工厂方法创建
      Map<String, List<String>> m2 = HashMap.newInstance();
```
  这里我们只讨论静态工厂方法的优点，缺点其其它不做比较
  最后，我们需要记住的是构造器和静态工厂方法都各有用处，后者更加灵活，通常情况下，应多考虑使用后者。

<span id='111'># Chapter1：创建和销毁对象</sapn>

## 2.遇到多个构造器参数时，考虑使用构建器

###### 什么是构建器？

我们先这样定义它，它是一个静态内部类，可根据不同参数灵活构建外部类的实例。

用一个类表示学生，当我们需要根据不同信息构建学生实例时，它可能是长这样的：

   public class Student {

       private String studentNum;   //  学号
       private String name;         //  姓名
       private int age;            //  年龄
       private char sex;           //  性别
       private String height;      //  身高
       private String weight;      //  体重
       private String address;     // 地址
       private String phone;       // 电话

       //空参
       public Student() {
       }

       //一个参数
       public Student(String name) {
           //省略
       }

       //两个参数
       public Student(String name, int age) {
           //省略
       }

       //三个参数
       public Student(String name, int age,String sex) {
           //省略
       }

       //四个参数，五个参数......
   }

这种重叠构造器模式从功能上来说可行，但有没有看起来很笨的感觉。

##

当然，我们还可以使用javaBeans的方式，提供set方法，使用的时候如下：

     Student student = new Student("001", "小羽");
     student.setAge(23);
     student.setSex('女');
     student.setAddress("湖北武汉");
     //......

但是，我们可以看到程序中构造过程是通过几次调用完成的。这意味着它有可能在构造的过程中被改变，这一点在多线程、高并发的程序中显得尤为重要。并且如果对象中含有final修饰的字段，那么javabean模式将不能对其执行setter操作。

##

因此，我们可以考虑使用没有重叠构造器那么笨，又比javaBeans更加安全的方法——构建器。

   public class Student {

       private String studentNum ;  //  学号   必填
       private String name;         //  姓名   必填
       private int age;             //  年龄
       private char sex;            //  性别
       private String height;       //  身高
       private String weight;       //  体重
       private String address;      //  地址
       private String phone;        //  电话

       private Student(Builder builder){
           this.studentNum = builder.studentNum;
           this.name = builder.name;
           this.age = builder.age;
           this.sex = builder.sex;
           //其它字段写法一样，此处省略.......
       }

       //构建器(静态内部类)
       static class Builder{
           private String studentNum ;
           private String name;
           private int age;
           private char sex;
           private String height;
           private String weight;
           private String address;
           private String phone;

           public Builder setStudentNum(String studentNum){
               this.studentNum = studentNum;
               return this;
           }

           public Builder setName(String name){
               this.name = name;
               return this;
           }

           public Builder setAge(int age){
               this.age = age;
               return this;
           }

           //其它字段写法一样，此处省略.......

            public Student build(){//build方法，生成一个需要被构建的类
               return new Student(this);
           }
       }
   }

使用如下：

    Student student = new Builder().setStudentNum("001")
                                   .setName("小羽")
                                   .setAge(23)
                                   .build();

build()为终结方法，用于最终返回Student实例。构建器写法其实也有点复杂，但是使用起来要方便许多。



最后，我们回到开头的定义:构建器是一个静态内部类，可根据不同参数灵活构建外部类的实例。需要记住的是，在实体类属性较多，特别是当大多是参数都是可选的时候，因考虑使用构建器



3.用私有构造器或者枚举类型强化Singleton属性

Singleton通常被用来代表那些本质上唯一的系统组件，在JDK1.5之前，实现Singleton有两种方法。这两种方法都要把构造器保持为私有的，并导出公有的静态成员，以便允许客户端能够访问该类的唯一实现。

##

第一种方式：1.构造器私有，2，提供一个公共的静态成员，并用final修饰。如下：

   public class Singleton {

       //在此将构造函数设置为private，防止该类在外部被实例化
       private Singleton(){
       }

       public static final Singleton INSTANCE = new Singleton();

   }

此写法外部只能调用类的静态函数，而不能new去创建对象;私有构造函数只能在函数内部调用，外部不能实例化。不过客户端任可以借助AccessibleObject.setAccessible()方法，通过反射机制调用私有构造器。如果需要抵御这种攻击，可以修改构造器，让他在被要求创建的第二个实例时候抛出异常。

##

第二种方式：1，构造器私有，2，提供一个私有的静态成员，提供一个公共的静态工厂方法  。如下：

   public class Singleton {

       private static final Singleton INSTANCE = new Singleton();

       private Singleton(){}

       public static Singleton getInstance(){
           return INSTANCE;
       }

   }

这其实就是饿汉式(立即加载)单例模式，一般为了防止多线程的时候创建多个实例，还会使用synchronized 关键字进行修饰。

对于静态方法getInstance()方法的所有调用，都会返回同一个对象的应用。这种写法的好处是很清楚地表明了该类是一个Singleton，但是这种写法有另一个好处是，可以添加其他的静态工厂方法返回另一个新的实例，这样就可以将该类变为非Signleton。为了使利用上述方法实现Singleton类变成是可序列化的，仅仅在声明时加上“implements Serializable”是不够的。为了维护并保证Singleton，必须声明所有实例都是瞬时transient的，并提供 一个readResolve方法。否则每次反序列化一个序列化的实例时，都会创建一个新的实例。即会导致一个假冒的对象。为了防止这种情况，需要在单例类中增加readResolve方法。延伸如下：

   public class Singleton implements Serializable {

     public static final Singleton INSTANCE = new Singleton();

     private Singleton() {
     }

     private Object readResolve() {
     return INSTANCE;
     }

   }

##

从JDK1.5开始，实现Singleton，只需编写一个包含单个元素的枚举类型。如下：

   public enum SingletonByEnum {
       INSTANCE;
   }

这种方法在功能上与公有域接近，但是它更简洁。是实现Singleton的最佳方式。



4.通过私有构造器强化不可实例化的能力

有的时候，我们需要编写一些只含有静态方法和静态域的类，如jdk中的Math类，Arrays类；这样的类的实例化是没有任何意义的。所以我们可以把这些类的构造器设置为私有的。 如下：

   public class Utils {

       private Utils(){
           throw new AssertionError("Utils cant not have instance");
       }



这样此类即使通过反射也无法实例化，当然此类也无法被继承。(因为子类无法调用其构造方法)



5.避免创建不必要的对象







Chapter6：枚举和注解

30.用枚举代替int常量



Chapter:异常

57.只针对异常的情况才使用异常



在不支持异常处理的语言，如C语言中，程序员为了检查可能发生的异常情况，需要在程序中编写代码来进行测试。而在java中，默认情况下，异常会输出错误信息，并终止程序。为了不影响程序运行，开发人员只需要在程序中捕获和抛出异常即可。



假设我们需要遍历一个数组t1：

   //标准模式
   for(String t:t1){
     System.out.println(t);
   }

   // 基于异常的模式
   String t1[]={"11","22"};
   try{
     int i=0;
     while(true){
       System.out.println(t1[i]);
       i++;
     }
   }catch(ArrayIndexOutOfBoundsException e){

   }


我们常见的处理方式是第一种。



但有人也会采用下面这种基于异常的方法，他们认为这样能够提高性能。但这其实是一种错误的处理方式，当我们使用java中的异常处理机制时，我们更应该了解其使用场景。

   1.异常机制的设计初衷是用于不正常的情形，所有很少有jvm实现试图对它们进行优化；

   2.把代码放在try-catch中反而阻止了jvm本来可能要执行的特定优化；

   3.对数组进行变量的标准模式并不会导致冗余的检查。有些jvm实现会将它们优化。




基于异常的模式除了会模糊代码的意图，难以阅读，还会降低它的性能，不能保证其正常工作，例如下面的代码:

   public static void main(String[] args) {
          String[] t1={"11","22"};
          String[] t2 = {};

          try{
              int i=0;
              while(true){
                  System.out.println(t1[i]);
                  System.out.println(t2[0]);
                  i++;
              }
          }catch(ArrayIndexOutOfBoundsException e){
          }
      }

Output:

   11

当使用异常模式时，产生了与这个bug相关的异常就会被捕捉到，并且被错误的解释为正常的循环终止条件。



总之，需要记住的是，java中的异常处理机制因该在会出现异常的地方使用，不应当随意使用。



58.对可恢复的情况使用受检异常，对编程错误使用运行时异常

先上图：





Error类及其子类，RuntimeException类及其子类为非受检异常，反之，为受检异常；

RuntimeException类及其子类为运行时异常，属非受检异常。



受检异常：代码逻辑没有错误，但程序运行时会因为IO等错误导致异常，你在编写程序阶段无法预料。如果不处理这些异常，程序将来肯定会出错。所以编译器会提示你要去捕获并处理这种可能发生的异常，不处理就不能通过编译。

非受检异常：程序逻辑本身有问题，比如数组越界、访问null对象，这种错误可以预料，应该通过编码避免。编译器不会强制你检查这种异常。



需要记住的是：对于编译无法通过的情况，我们应该采用响应的受检异常来进行捕获或抛出；而对于编程错误，逻辑错误等异等情况（编译能通过），应采用RuntimeException及其子类。



59.避免不必要地使用使用受检异常

受检异常是java中一项很好的特性，能帮助我们提前预知错误，减少不必要的麻烦。





60.有限使用标准的异常
