package btvn.bai6;

import btvn.bai3.User;

public class Main {
    public static void main(String[] args) {
        UserProcessor newUser = UserUtils::convertToUpperCase;

        User createUser = new User("Hoan" , 19 , "hahahahah");

        String result = newUser.process(createUser);
        System.out.println(result);
    }
}
