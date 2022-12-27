
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

public class CategoriesTest {
    Categories categories = new Categories();
    CategoryStorage storage = new CategoryStorage();
    Statistic statistic = new Statistic();
    String input = "{\"title\": \"булка\", \"date\": \"2022.02.08\", \"sum\": 200}";


    @Test
    public void statisticsTest() {
        Map<String, Integer> map = categories.statistics(input, statistic, storage);
        String result = statistic.getTitleCategoryMap().get("булка");
        Assertions.assertEquals("другое" ,result);
    }

    @Test
    public void getMaxCategoryTest() {
        storage.addCategoryMap("другое", 100);
        Optional<Map.Entry<String,Integer>> result = categories.getMaxCategory(storage);

        Assertions.assertEquals(100,result.get().getValue());
    }
}
