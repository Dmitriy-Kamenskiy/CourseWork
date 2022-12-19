import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Categories {
    private String title;
    private String category;
    private int sum;
    private Map<String, String> titleCategoryMap = new HashMap<>();
    private Map<String, Categories> categoryMap = new HashMap<>();
    public Categories(){}
    public Categories(String title, String category, int sum) {
        this.title = title;
        this.category = category;
        this.sum = sum;
    }

    public void addSum(int sum) {
        this.sum += sum;
    }
    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
    public  Map<String, Categories> addCategoryMap(String category, String title, int sum) {
        categoryMap.put(category, new Categories(title, category, sum));
        return categoryMap;
    }
    public Map<String, String> addTitleCategoryMap(String title, String category) {
        titleCategoryMap.put(title, category);
        return titleCategoryMap;
    }
    public Map<String, Categories> statistics (String input) {
        JsonElement element =  JsonParser.parseString(input).getAsJsonObject().get("title");
        JsonElement sum = JsonParser.parseString(input).getAsJsonObject().get("sum");
        if (titleCategoryMap.containsKey(element.getAsString())) {
            for (String item : titleCategoryMap.keySet()) {
                if (item.equals(element.getAsString())) {
                    String cat = titleCategoryMap.get(item);
                    categoryMap.get(cat).addSum(sum.getAsInt());
                    break;
                }
            }
        }else {
            titleCategoryMap.put(element.getAsString(), "другое");
            if (categoryMap.containsKey("другое")) {
                categoryMap.get("другое").addSum(sum.getAsInt());
            }else {
                categoryMap.put("другое",new Categories(element.getAsString(), "другое", sum.getAsInt()));
            }
        }
        return categoryMap;
    }
    public Optional<Categories> getMaxCategory() {
        Optional<Categories> maxCategories = categoryMap.values().stream().max(Comparator.comparingInt(Categories::getSum));
        maxCategories.ifPresent(Categories::getCategory);
        return maxCategories;
    }
}
