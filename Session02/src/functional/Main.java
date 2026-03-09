package functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        IColorable obj = new Shape();

        obj.draw();
        obj.printColor("red");
        IColorable.erase();

//        Consumer
//        Predicate
//        Function
//        Supplier

        // Lambda expression : là cú pháp viết gọn của 1 method dùng để
        IFunctional lb = () -> {};
        // trước java 8
        IFunctional i1 = new Cat();
        IFunctional i2 = new IFunctional() {
            @Override
            public void save() {

            }
        };


        Comparator<Cat> com1 = (c1,c2) -> -1;
        Collections.sort(new ArrayList<>() , (o1,o2) -> 2);

        int[] arr = {1,2,3,4,5};
        Arrays.stream(arr).map(value -> value*value).forEach(t -> System.out.println(t));
    }
}
