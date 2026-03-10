package btvn.bai2;

import btvn.bai1.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Student stu1 = new Student("alice" , "ksdk@gmail.com" , true);
        Student stu2 = new Student("bob" , "ksdk@yahoo.com" , false);
        Student stu3 = new Student("charlie " , "ksdk@gmail.com" , true);

        List<Student> users = new ArrayList<>();
        users.add(stu1);
        users.add(stu2);
        users.add(stu3);

        users.stream().filter(user -> user.email().endsWith("@gmail.com")).forEach(System.out::println);
    }
}
