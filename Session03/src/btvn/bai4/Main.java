package btvn.bai4;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<User> list = List.of(
                new User("P01" , "Hoàn"),
                new User("P02" , "Hoàn"),
                new User("P03" , "Hàn"),
                new User("P04" , "Hoà")
        );

        Set<String> set = new HashSet<>();

        List<User> result = list.stream().filter(u -> set.add(u.username())).collect(Collectors.toList());
        System.out.println(result);
    }
}
