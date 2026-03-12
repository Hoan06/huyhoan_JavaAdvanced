package btvn.bai3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserProcessorTest {
    UserProcessor userProcessor;

    @BeforeEach
    void setUp(){
        userProcessor = new UserProcessor();
    }

    @Test
    @DisplayName("TH hợp lệ 1")
    void checkEmail1() {
        assertEquals("user@gmail.com" , userProcessor.checkEmail("user@gmail.com"));
    }

    @Test
    @DisplayName("TH hợp lệ 2")
    void checkEmail2() {
        assertEquals("example@gmail.com" , userProcessor.checkEmail("Example@Gmail.com"));
    }

    @Test
    @DisplayName("TH không hợp lệ")
    void checkEmail3() {
        assertThrows(IllegalArgumentException.class, () -> {userProcessor.checkEmail("user@");});
    }
}