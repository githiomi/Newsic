package githiomi.dhosiolux.newsic.models;

public class Constants {

    // Application Constants
    public static final String NEWS_API_BASE_URL = "https://newsapi.org/";

    public static final String[] NEWS_CATEGORIES = {"all", "business", "entertainment", "general", "health", "science", "sports", "technology"};

    public static final String COUNTRY = "us";

    public static final String LANGUAGE = "en";

    public static final String EXCLUDED_DOMAIN = "stackoverflow.com";

    public static final String SORT_BY = "publishedAt";

    //    public static final String NEWS_API_KEY = BuildConfig.NEWS_API_APIKEY;
    public static final String NEWS_API_KEY = "e56d55726f354d99bbaff93c5e374f5b";

    public static final String GET_ALL_NEWS_URL = "https://newsapi.org/v2/top-headlines?country=" +
            COUNTRY + "&excludeDomains=" + EXCLUDED_DOMAIN + "&sortBy=" + SORT_BY + "&language=" +
            LANGUAGE + "&apiKey=" + NEWS_API_KEY;

    public static final String ARTICLE_WRAP = "wrapped article";

    // Prevent init of the class
    private Constants() {
        // Cannot instantiate the class
    }
}
