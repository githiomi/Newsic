package githiomi.dhosiolux.newsic.models;

import java.util.List;

import lombok.Data;

// This is the structure of the data gotten from the API call
// It contains the full news structure including the status code
@Data
public class News {

    // Instance variables
    private String status;
    private int totalResults;
    private List<Article> articles;

    // Constructor

    public News() {
    }

    public News(String status, int totalResults, List<Article> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    // Getters, Setters, ToString and HashAndEquals methods handled by Lombok
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
