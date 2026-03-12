import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PracticeTest {

    @Test
    @DisplayName("Số nhỏ hơn 2 : không phải số nguyên tố")
    void testPrimeLessThan2() {
        assertEquals(false , Practice.checkElement(1));
    }

    @Test
    @DisplayName("2 là số nguyên tố")
    void testPrimeTwo() {
        assertEquals(true , Practice.checkElement(2));
    }

    @Test
    void fibonaci() {
        assertEquals(13, Practice.fibonaci(7));
    }

    @Test
    void fibonaciFail() {
        assertEquals(10, Practice.fibonaci(5));
    }

    @Test
    void fibonaciThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Practice.fibonaci(-5);
        });
    }

}