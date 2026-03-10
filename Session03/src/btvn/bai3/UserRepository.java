package btvn.bai3;


import btvn.bai1.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    private static final List<Student> users = new ArrayList<>();

    public UserRepository() {
        users.add(new Student("alice", "alice@gmail.com", true));
        users.add(new Student("bob", "bob@yahoo.com", false));
        users.add(new Student("charlie", "charlie@gmail.com", true));
    }

    public static Optional<Student> findUserByUsername(String username){
        return users.stream().filter(name -> name.userName().equals(username)).findFirst();
    }
}
