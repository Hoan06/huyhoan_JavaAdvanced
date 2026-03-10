package btvn.bai1;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Student stu1 = new Student("alice" , "ksdk@gmail.com" , true);
        Student stu2 = new Student("bob" , "ksdk@gmail.com" , false);
        Student stu3 = new Student("charlie " , "ksdk@gmail.com" , true);

        List<Student> users = new ArrayList<>();
        users.add(stu1);
        users.add(stu2);
        users.add(stu3);

        users.stream().forEach(System.out::println);
    }
}
