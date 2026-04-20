import com.example.Feline;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FelineParameterizedTest {

    private final int kittensCount;

    public FelineParameterizedTest(int kittensCount) {
        this.kittensCount = kittensCount;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {0},
                {1},
                {3},
                {5},
                {10}
        };
    }

    @Test
    public void getKittensParameterizedTest() {
        Feline feline = new Feline();
        int actual = feline.getKittens(kittensCount);
        assertEquals("Количество котят должно быть " + kittensCount, kittensCount, actual);
    }
}
