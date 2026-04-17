import com.example.Feline;
import com.example.Lion;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class LionTest {

    @Mock
    Feline feline;

    @Before
    public void setUp() throws Exception {
        Field field = Lion.class.getDeclaredField("feline");
        field.setAccessible(true);
    }

    private void injectMock(Lion lion, Feline mock) throws Exception {
        Field field = Lion.class.getDeclaredField("feline");
        field.setAccessible(true);
        field.set(lion, mock);
    }

    @Test
    public void getKittensTest() throws Exception {
        Lion lion = new Lion("Самка");
        injectMock(lion, feline);

        Mockito.when(feline.getKittens()).thenReturn(3);

        int actual = lion.getKittens();
        int expected = 3;

        assertEquals("Количество львят должно быть 3", expected, actual);
        Mockito.verify(feline, Mockito.times(1)).getKittens();
    }

    @Test
    public void getFoodTest() throws Exception {
        Lion lion = new Lion("Самец");
        injectMock(lion, feline);

        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        Mockito.when(feline.getFood("Хищник")).thenReturn(expectedFood);

        List<String> actualFood = lion.getFood();

        assertEquals("Лев должен есть хищника", expectedFood, actualFood);
        Mockito.verify(feline, Mockito.times(1)).getFood("Хищник");
    }

    @Test
    public void doesHaveManeMaleTest() throws Exception {
        Lion lion = new Lion("Самец");
        boolean expected = true;
        boolean actual = lion.doesHaveMane();
        assertEquals("У самца должна быть грива", expected, actual);
    }

    @Test
    public void doesHaveManeFemaleTest() throws Exception {
        Lion lion = new Lion("Самка");
        boolean expected = false;
        boolean actual = lion.doesHaveMane();
        assertEquals("У самки не должно быть гривы", expected, actual);
    }

    @Test
    public void constructorThrowsExceptionForInvalidSexTest() {
        Exception exception = assertThrows(Exception.class, () -> {
            new Lion("Котик");
        });
        String expectedMessage = "Используйте допустимые значения пола животного - самей или самка";
        assertEquals("Должно быть исключение для неверного пола", expectedMessage, exception.getMessage());
    }
}