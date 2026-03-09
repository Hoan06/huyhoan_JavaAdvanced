package btvn.bai4;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        User user = new User();

        Function<User,String> userName = User::getName;

        Consumer<String> out = System.out::println;

        Supplier<User> newUser = User::new;

    }
}
