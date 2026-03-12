package btvn.bai1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserValidatorTest {

    @Test
    @DisplayName("TC01")
    void check1() {
        UserValidator userValidator = new UserValidator();
        assertTrue(userValidator.isValidUsername("user123"));
    }

    @Test
    @DisplayName("TC02")
    void check2() {
        UserValidator userValidator = new UserValidator();
        assertFalse(userValidator.isValidUsername("abc"));
    }
    @Test
    @DisplayName("TC03")
    void check3() {
        UserValidator userValidator = new UserValidator();
        assertFalse(userValidator.isValidUsername("user name"));
    }

}