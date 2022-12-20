
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

public class CategoriesTest {
    Categories categories = new Categories();
    Statistic statistic = new Statistic();
    String input = "{\"title\": \"булка\", \"date\": \"2022.02.08\", \"sum\": 200}";


    @Test
    public void statisticsTest() {
        Map<String, Categories> map = categories.statistics(input, statistic, categories);
        String result = statistic.getTitleCategoryMap().get("булка");
        Assertions.assertEquals("другое" ,result);
    }

    @Test
    public void getMaxCategoryTest() {
        categories.addCategoryMap(/*"пиво", */"другое", 100);
        Optional<Categories> result = categories.getMaxCategory();

        Assertions.assertEquals(100,result.get().getSum());
    }
}
