package btvn.bai4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserPasswordTest {

    UserPassword userPassword;

    @BeforeEach
    void setUp() {
        userPassword = new UserPassword();
    }

    @Test
    void testPasswordStrength() {

        assertAll(

                () -> assertEquals("Mạnh",
                        userPassword.evaluatePasswordStrength("Abc123!@")),

                () -> assertEquals("Trung bình",
                        userPassword.evaluatePasswordStrength("abc123!@")),

                () -> assertEquals("Trung bình",
                        userPassword.evaluatePasswordStrength("ABC123!@")),

                () -> assertEquals("Trung bình",
                        userPassword.evaluatePasswordStrength("Abcdef!@")),

                () -> assertEquals("Trung bình",
                        userPassword.evaluatePasswordStrength("Abc12345")),

                () -> assertEquals("Yếu",
                        userPassword.evaluatePasswordStrength("Ab1!")),

                () -> assertEquals("Yếu",
                        userPassword.evaluatePasswordStrength("password")),

                () -> assertEquals("Yếu",
                        userPassword.evaluatePasswordStrength("ABC12345"))
        );
    }
}