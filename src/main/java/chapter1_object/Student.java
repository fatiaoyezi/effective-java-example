package chapter1_object;

import lombok.Data;

/**
 * @author fatiaoyezi
 * @title:
 * @projectName effective-java-example
 * @description:  遇到多个构造器参数时要考虑用构建器----第二条
 * @date 2019/7/16 10:20
 */
@Data
public class Student {

    private String studentNum ;   //  学号  必填
    private String name;         //  姓名   必填
    private int age;            //  年龄
    private char sex;           //  性别
    private String height;      //  身高
    private String weight;      //  体重
    private String address;     // 地址
    private String phone;       // 电话

    private Student(Builder builder){
        this.studentNum = builder.studentNum;
        this.name = builder.name;
        this.age = builder.age;
        this.sex = builder.sex;
        //其它字段写法一样，此处省略.......
    }

    //构建器(内部类)
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


    //两个参数
    public Student(String studentNum,String name) {
        //省略
    }

    //三个参数
    public Student(String studentNum,String name, int age) {
        //省略
    }

    //四个个参数
    public Student(String studentNum,String name, int age,String sex) {
        //省略
    }

    //五个参数，六个参数......

    public static void main(String[] args) {
       /* Student student = new Student("001", "小羽");
        student.setAge(23);
        student.setSex('女');
        student.setAddress("湖北武汉");*/
        //......
        Student student = new Builder().setStudentNum("001")
                                        .setName("小羽")
                                        .setAge(23)
                                        .build();

        System.out.println(student);
    }

}
