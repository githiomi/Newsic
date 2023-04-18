package githiomi.dhosiolux.newsic.models;

// This class will hold the name, image and url to each category
public class Category {

    // Instance variables
    private String categoryName;
    private String categoryUrl;
    private String categoryImageUrl;

    // Constructor
    public Category(String categoryName, String categoryUrl, String categoryImageUrl) {
        this.categoryName = categoryName;
        this.categoryUrl = categoryUrl;
        this.categoryImageUrl = categoryImageUrl;
    }

    // Getter and setter methods
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryUrl() {
        return categoryUrl;
    }

    public void setCategoryUrl(String categoryUrl) {
        this.categoryUrl = categoryUrl;
    }

    public String getCategoryImageUrl() {
        return categoryImageUrl;
    }

    public void setCategoryImageUrl(String categoryImageUrl) {
        this.categoryImageUrl = categoryImageUrl;
    }
}
