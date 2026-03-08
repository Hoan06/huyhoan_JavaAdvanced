package btvn.bai6;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        User user = new User();

        try {

            user.setName("Hoan");
            user.setAge(-2);

            user.printUserInfo();

            UserService.processUserData(user);

        } catch (InvalidAgeException e) {

            Logger.logError(e.getMessage());

        } catch (IOException e) {

            Logger.logError("Lỗi IO: " + e.getMessage());

        }

    }
}
