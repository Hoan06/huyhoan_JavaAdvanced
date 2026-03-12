package btth;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @BeforeEach
    void setUp() {
        UserService userService = new UserService();
    }

    @AfterEach
    void tearDown() {
    }

//    @Test
//    @DisplayName("Kiểm tra thêm thành công")
//    void addUserTest() {
//        assertEquals(1,);
//    }

    @Test
    void findUserById() {
    }

    @Test
    void isValidEmail() {
    }
}