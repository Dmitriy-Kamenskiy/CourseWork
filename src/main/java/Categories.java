public class Categories {
    private String title;
    private String category;
    private int sum;

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


}
