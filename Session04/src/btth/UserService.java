package btth;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static final List<User> users = new ArrayList<>();

    public void addUser(User user) {
        if (user.username() == null || user.username().isBlank()) {
            throw new IllegalArgumentException("Username không được null hoặc rỗng !");
        }
        users.add(user);
    }

    public User findUserById(int id){
        for (User user : users) {
            if (user.id() == id) {
                return user;
            }
        }
        return null;
    }

    public boolean isValidEmail(String email) {
        return email.contains("@") && !email.isBlank();
    }
}
