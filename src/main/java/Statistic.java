import java.util.HashMap;
import java.util.Map;

public class Statistic {
    private String title;
    private String category;
    private int sum;
    private  Map<String, String> titleCategoryMap = new HashMap<>();

    public Statistic(){};

    public Map<String, String> getTitleCategoryMap() {
        return titleCategoryMap;
    }

    public void addTitleCategoryMap(String title, String category) {
        titleCategoryMap.put(title, category);
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public void addSum(int sum) {
        this.sum += sum;
    }
}
