package btvn.bai1;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        // predicate
        Predicate<User> isAdmin = user -> {return user.getName().equals("admin");};

        // function
        Function<User , String> getUserName = user -> {return user.getName();};

        // consumer
        Consumer<User> log = user -> {System.out.println(user);};

        // Suplier
        Supplier<User> newUser = () ->  new User("36" , 18);
    }
}
