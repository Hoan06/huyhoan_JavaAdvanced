package btvn.bai3;


import btvn.bai1.Student;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        Optional<Student> users = userRepository.findUserByUsername("alice");

        if (users.isPresent()){
            System.out.println("Welcome " + users.get().userName());
        } else {
            System.out.println("Guest login");
        }
    }
}
