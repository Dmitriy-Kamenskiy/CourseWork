
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

public class CategoriesTest {
    Categories categories = new Categories();
    String input = "{\"title\": \"булка\", \"date\": \"2022.02.08\", \"sum\": 200}";


    @Test
    public void statisticsTest() {
        Map<String, Categories> result = categories
                .statistics(input);

        Assertions.assertEquals("булка" ,result.get("другое").getTitle());
    }

    @Test
    public void getMaxCategoryTest() {
        categories.addCategoryMap("пиво", "другое", 100);
        Optional<Categories> result = categories.getMaxCategory();

        Assertions.assertEquals(100,result.get().getSum());
    }
}
