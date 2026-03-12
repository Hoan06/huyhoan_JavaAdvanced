package btvn.bai6;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    UserService userService = new UserService();
    List<User> users = new ArrayList<>();

    @AfterEach
    void tearDown() {
        users.clear();
    }

    @Test
    void updateProfile_success_whenEmailAndBirthDateValid() {

        User existing = new User("Hoan", "hoan@gmail.com",
                LocalDate.of(2000,1,1));

        users.add(existing);

        UserProfile newProfile =
                new UserProfile("Hoan Nguyen",
                        "new@gmail.com",
                        LocalDate.of(2000,1,1));

        User result =
                userService.updateProfile(existing,newProfile,users);

        assertNotNull(result);
    }

    @Test
    void updateProfile_fail_whenBirthDateInFuture() {

        User existing =
                new User("Hoan","hoan@gmail.com",
                        LocalDate.of(2000,1,1));

        users.add(existing);

        UserProfile newProfile =
                new UserProfile("Hoan",
                        "new@gmail.com",
                        LocalDate.now().plusDays(1));

        User result =
                userService.updateProfile(existing,newProfile,users);

        assertNull(result);
    }

    @Test
    void updateProfile_fail_whenEmailDuplicated() {

        User existing =
                new User("Hoan","hoan@gmail.com",
                        LocalDate.of(2000,1,1));

        User otherUser =
                new User("Nam","nam@gmail.com",
                        LocalDate.of(1999,1,1));

        users.add(existing);
        users.add(otherUser);

        UserProfile newProfile =
                new UserProfile("Hoan",
                        "nam@gmail.com",
                        LocalDate.of(2000,1,1));

        User result =
                userService.updateProfile(existing,newProfile,users);

        assertNull(result);
    }

    @Test
    void updateProfile_success_whenEmailSameAsCurrent() {

        User existing =
                new User("Hoan","hoan@gmail.com",
                        LocalDate.of(2000,1,1));

        users.add(existing);

        UserProfile newProfile =
                new UserProfile("Hoan Nguyen",
                        "hoan@gmail.com",
                        LocalDate.of(2000,1,1));

        User result =
                userService.updateProfile(existing,newProfile,users);

        assertNotNull(result);
    }

    @Test
    void updateProfile_success_whenUserListEmpty() {

        User existing =
                new User("Hoan","hoan@gmail.com",
                        LocalDate.of(2000,1,1));

        UserProfile newProfile =
                new UserProfile("Hoan Nguyen",
                        "new@gmail.com",
                        LocalDate.of(2000,1,1));

        User result =
                userService.updateProfile(existing,newProfile,users);

        assertNotNull(result);
    }

    @Test
    void updateProfile_fail_whenEmailDuplicateAndBirthDateFuture() {

        User existing =
                new User("Hoan","hoan@gmail.com",
                        LocalDate.of(2000,1,1));

        User otherUser =
                new User("Nam","nam@gmail.com",
                        LocalDate.of(1999,1,1));

        users.add(existing);
        users.add(otherUser);

        UserProfile newProfile =
                new UserProfile("Hoan",
                        "nam@gmail.com",
                        LocalDate.now().plusDays(1));

        User result =
                userService.updateProfile(existing,newProfile,users);

        assertNull(result);
    }
}