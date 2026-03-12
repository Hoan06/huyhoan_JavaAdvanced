import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @BeforeEach
    void setUp() {
        System.out.println("Before Each");
    }

    @AfterEach
    void tearDown() {
        System.out.println("After Each");
    }

    @Test
    @DisplayName("Case 1")
    void sum() {
        Main main = new Main();
        Assertions.assertEquals(3,main.sum(1,2));
        Assertions.assertEquals(5,main.sum(3,2));
        Assertions.assertEquals(10,main.sum(5,5));
//        Assertions.assertEquals(2,main.sum(2,5));
    }

    @Test
    void sum2() {
        System.out.println("Test2");
    }

}