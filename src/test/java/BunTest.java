import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;

@RunWith(Parameterized.class)
public class BunTest {

    private final String name;
    private final float price;
    private Bun bun;

    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0} {1}")
    public static Object[][] setBun() {
        return new Object[][]{{"Bun", 1}, {null, 0}, {"B", -1}, {"", -0.1F}, {" ", 1.1F},};
    }

    @Before
    public void setUp() {
        bun = new Bun(name, price);
    }

    @Test
    public void getNameTest() {
        String actualName = bun.getName();
        Assert.assertEquals("Нет такого наименования", name, actualName);
    }

    @Test
    public void getPriceTest() {
        float actualPrice = bun.getPrice();
        Assert.assertEquals("Стоимость неверна", price, actualPrice, 0.0);
    }
}