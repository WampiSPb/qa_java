import com.example.Feline;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FelineParameterizedTest {

    private final int inputKittens;
    private final int expectedKittens;

    public FelineParameterizedTest(int inputKittens, int expectedKittens) {
        this.inputKittens = inputKittens;
        this.expectedKittens = expectedKittens;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {0, 0},
                {1, 1},
                {3, 3},
                {5, 5},
                {10, 10}
        };
    }

    @Test
    public void getKittensParameterizedTest() {
        Feline feline = new Feline();
        int actual = feline.getKittens(inputKittens);
        assertEquals("Количество котят должно быть " + expectedKittens, expectedKittens, actual);
    }
}
