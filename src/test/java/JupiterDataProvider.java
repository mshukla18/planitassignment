import org.testng.annotations.DataProvider;

import java.util.Map;

public class JupiterDataProvider {
    @DataProvider(name="contact")
    public Object[][] getAllFeedbackData() {
        return new Object[][] {
                {"John", "test_example@example.com", "Some text"}
        };
    }

    @DataProvider(name="shoppingCart")
    public Object[][] getAllShoppingData() {
        Map<String, Integer> testData = Map.of("Stuffed Frog",2,
                "Fluffy Bunny", 5,
                "Valentine Bear", 3);
        return new Object[][] {
                {testData}
        };
    }
}
