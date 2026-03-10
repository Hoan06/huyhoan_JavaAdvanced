package btvn.bai5;

import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<User> users = List.of(
                new User("P01" , "sjjsdjds"),
                new User("P02" , "sjjsd"),
                new User("P03" , "sjjsjds"),
                new User("P04" , "sjjjds"),
                new User("P05" , "sjds"),
                new User("P06" , "sjjjds"),
                new User("P07" , "sjjds")
        );

        List<User> result = users.stream().sorted(Comparator.comparing((User u) -> u.username().length()).reversed()).limit(3).toList();
        System.out.println(result);
    }
}
