package methodreferences;

import java.util.*;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        // tham chiếu của 1 phương thức

        // Duyệt qua 1 danh sách tên học sinh và in ra màn hình
        List<String> names = Arrays.asList("Nam" , "Sơn" , "Trang 36");
        for(String name : names){
            System.out.println(name);
        }
        Iterator<String> iterator = names.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        // Lambda expression
//        names.forEach(s -> System.out.println(s));
        names.forEach(Printer::Print); // thay thế lambda expression

        // cách sử dụng
        /*
            ClassName :: method
            objName :: method
            ClassName :: new (hàm tạo)
         */

        // Biến đổi thành danh sách student
        List<Student> studentList = names.stream().map(Student::new).toList();
        System.out.println(studentList);

        // Lọc ra những sinh viên trong tên có chứa chữ n
        for (Student student : studentList){
            if (student.getName().contains("n")){
                System.out.println(student);
            }
        }

        studentList.stream().filter(student -> student.getName().contains("n")).sorted(Comparator.comparing(Student::getName)).forEach(System.out::println);

        // 4 phương thức cơ bản : map , filter , foreach , reduce
        Random rad = new Random();
        List<Integer> intergers = Stream.generate(() -> rad.nextInt(100)).limit(20).toList(); // Supplier

        System.out.println("-----------------------------------");

        // foreach
        intergers.forEach(a -> System.out.println(a)); // consumer

        System.out.println("-----------------------------------");

        // filter
        intergers.stream().filter(a -> a % 3 == 0).forEach(System.out::println); // predicate

        System.out.println("-----------------------------------");

        // map
        intergers.stream().map(a -> Math.pow(a,3)).forEach(System.out::println); // Functional

        System.out.println("-----------------------------------");

        // reduce
        long total = intergers.stream().reduce(0,(temp , a) -> temp + a);
        System.out.println(total);
    }
}
