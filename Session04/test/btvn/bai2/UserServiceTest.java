package btvn.bai2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    UserService userService = new UserService();

    @Test
    @DisplayName("Tuổi > 18")
    void test1() {
        assertEquals(true,userService.checkRegistrationAge(18));
    }

    @Test
    @DisplayName("Tuổi < 18")
    void test2() {
        assertEquals(false,userService.checkRegistrationAge(17));
    }

    @Test
    @DisplayName("Tuổi nhỏ hơn 0")
    void test3() {
        assertThrows(IllegalArgumentException.class, () -> userService.checkRegistrationAge(-1));
    }
}