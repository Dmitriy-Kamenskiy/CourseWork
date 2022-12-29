import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.*;

public class Categories {
    private String category;
    private int sum;

    public Categories(){}
    public Categories(String category, int sum) {

        this.category = category;
        this.sum = sum;
    }

    public void addSum(int sum) {
        this.sum += sum;
    }

    public String getCategory() {
        return this.category;
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


    public Map<String, Integer> statistics (String input, Statistic statistic, CategoryStorage storage) {
        Map<String, String> titleCategoryMap = statistic.getTitleCategoryMap();
        Map<String, Integer>storageMap = storage.getCategoryMap();
        JsonElement element =  JsonParser.parseString(input).getAsJsonObject().get("title");
        JsonElement sum = JsonParser.parseString(input).getAsJsonObject().get("sum");
        if (titleCategoryMap.containsKey(element.getAsString())) {
            for (String item : titleCategoryMap.keySet()) {
                if (item.equals(element.getAsString())) {
                    String cat = titleCategoryMap.get(item);
                    Integer sumItem = storageMap.get(cat) + sum.getAsInt();
                    storageMap.put(cat,sumItem);

                    break;
                }
            }
        }else {
            titleCategoryMap.put(element.getAsString(), "другое");
            if (storageMap.containsKey("другое")) {
                Integer sumItem = storageMap.get("другое") + sum.getAsInt();
                storageMap.put("другое", sumItem);

            }else {
                storageMap.put("другое",  sum.getAsInt());
            }
        }
        return storageMap;
    }
    public Optional<Map.Entry<String, Integer>> getMaxCategory(CategoryStorage storage) {

        Optional<Map.Entry<String, Integer>> maxCategories = storage.getCategoryMap().entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue));
         maxCategories.ifPresent(t -> getCategory());
        return maxCategories;
    }
}
