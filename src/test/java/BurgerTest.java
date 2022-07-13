import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.mockito.Mockito.*;
import static praktikum.IngredientType.FILLING;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    private Burger burger;

    @Mock
    Bun bun;

    @Mock
    Ingredient ingredient;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsTest() {
        burger.setBuns(bun);
        Assert.assertEquals("Булочка не выбрана",burger.bun, bun);
    }

    @Test
    public void addIngredientTest() {
        burger.addIngredient(ingredient);
        Assert.assertNotNull("Ингредиент не добавлен",burger.ingredients);
    }

    @Test
    public void removeIngredientTest() {
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        Assert.assertTrue("Ингредиент не удален",burger.ingredients.isEmpty());
    }


    @Test
    public void moveIngredientTest() {
        String expectedName = "Луна";
        Ingredient mock = mock(Ingredient.class, withSettings().name(expectedName));
        burger.addIngredient(ingredient);
        burger.addIngredient(mock);
        burger.moveIngredient(1, 0);
        Assert.assertEquals("Ингредиент не перемещен",expectedName, burger.ingredients.get(0).toString());
    }

    @Test
    public void getPriceTest() {
        float price = 100;
        float expectedPrice = price * 2 + price;
        when(bun.getPrice()).thenReturn(price);
        when(ingredient.getPrice()).thenReturn(price);
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        Assert.assertEquals("Стоимость неверна",expectedPrice, burger.getPrice(), 0.0);
    }

    @Test
    public void getReceiptTest() {
        String nameBun = "Булочка с кунжутом";
        String nameIngredient = "перец";
        IngredientType ingredientType = FILLING;
        float price = ingredient.getPrice();
        String expectedReceipt = "(==== " + nameBun + " ====)\r\n" +
                "= filling " + nameIngredient + " =\r\n" +
                "(==== " + nameBun + " ====)\r\n" +
                "\r\n" + String.format("Price: %f%n", price);
        burger.setBuns(bun);
        when(bun.getName()).thenReturn(nameBun);
        when(ingredient.getName()).thenReturn(nameIngredient);
        when(ingredient.getType()).thenReturn(ingredientType);
        burger.addIngredient(ingredient);
        Assert.assertEquals("Нет такого рецепта",expectedReceipt, burger.getReceipt());
    }
}