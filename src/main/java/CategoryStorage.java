import java.util.HashMap;
import java.util.Map;

public class CategoryStorage {
    private Map<String, Integer> categoryMap = new HashMap<>();

    public Map<String, Integer> getCategoryMap() {
        return categoryMap;
    }

    public CategoryStorage() {
    }

    public CategoryStorage(Map<String, Integer> categoryMap) {
        this.categoryMap = categoryMap;
    }
    public  Map<String, Integer> addCategoryMap(String category, int sum) {
        this.categoryMap.put(category, sum);
        return this.categoryMap;
    }
}
