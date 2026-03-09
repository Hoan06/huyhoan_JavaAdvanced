package btvn.bai6;

import btvn.bai3.User;

@FunctionalInterface
public interface UserProcessor {
    String process(User u);
}
