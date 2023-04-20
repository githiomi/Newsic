package githiomi.dhosiolux.newsic.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import githiomi.dhosiolux.newsic.adapters.ArticleAdapter;
import githiomi.dhosiolux.newsic.adapters.CategoryAdapter;
import githiomi.dhosiolux.newsic.databinding.ActivityMainBinding;
import githiomi.dhosiolux.newsic.interfaces.RetrofitAPI;
import githiomi.dhosiolux.newsic.models.Article;
import githiomi.dhosiolux.newsic.models.Category;
import githiomi.dhosiolux.newsic.models.Constants;
import githiomi.dhosiolux.newsic.models.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CategoryAdapter.OnCategoryClickInterface {

    // Layout Views
    RecyclerView categoriesRV, articlesRV;
    RelativeLayout trendingLoading, trendingError;

    // Adapters
    CategoryAdapter categoryAdapter;
    ArticleAdapter articleAdapter;

    // Arraylists
    List<Category> categoryList;
    List<Article> articleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // View Binding set up
        ActivityMainBinding mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        // Set Content view
        setContentView(mainBinding.getRoot());

        // Binding Views
        categoriesRV = mainBinding.RVCategories;
        articlesRV = mainBinding.RVTrendingNews;
        trendingLoading = mainBinding.RLLoading;
        trendingError = mainBinding.RLError;

        // Init variables
        categoryList = new ArrayList<>();
        articleList = new ArrayList<>();

        // Initialize the categories
        initCategories();

        // Make API call to get all the news when the application loads
        getNewsArticles("all");
    }

    /**
     * Interface passed from the category adapter to alter the main activity view
     *
     * @param categoryPosition the position of a category item in the list
     */
    @Override
    public void onCategoryClick(int categoryPosition) {

        // Get the position when a category is clicked
        String categoryName = Constants.NEWS_CATEGORIES[categoryPosition];

        // Make an API call with the category name
        getNewsArticles(categoryName);
    }

    /**
     * The method to set all the category names and background images
     */
    public void initCategories() {

        // Init the categories
        categoryList = Arrays.asList(
                new Category(Constants.NEWS_CATEGORIES[0], "https://images.unsplash.com/photo-1451187580459-43490279c0fa?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTV8fGdlbmVyYWx8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60"),
                new Category(Constants.NEWS_CATEGORIES[1], "https://images.unsplash.com/photo-1630464373951-e48d097f3b7d?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1yZWxhdGVkfDl8fHxlbnwwfHx8fA%3D%3D&auto=format&fit=crop&w=500&q=60"),
                new Category(Constants.NEWS_CATEGORIES[2], "https://images.unsplash.com/photo-1514525253161-7a46d19cd819?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8ZW50ZXJ0YWlubWVudHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60"),
                new Category(Constants.NEWS_CATEGORIES[3], "https://media.istockphoto.com/id/1390033645/photo/world-news-background-which-can-be-used-for-broadcast-news.jpg?b=1&s=170667a&w=0&k=20&c=glqFWZtWU4Zqyxd8CRu5_Or81zqwe7cyhturXaIFEOA="),
                new Category(Constants.NEWS_CATEGORIES[4], "https://images.unsplash.com/photo-1535914254981-b5012eebbd15?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTd8fGhlYWx0aHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60"),
                new Category(Constants.NEWS_CATEGORIES[5], "https://images.unsplash.com/photo-1507413245164-6160d8298b31?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8c2NpZW5jZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60"),
                new Category(Constants.NEWS_CATEGORIES[6], "https://images.unsplash.com/photo-1461896836934-ffe607ba8211?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8c3BvcnRzfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60"),
                new Category(Constants.NEWS_CATEGORIES[7], "https://images.unsplash.com/photo-1526374965328-7f61d4dc18c5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTV8fHNjaWVuY2V8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60")
        );

        passToCategoriesAdapter(categoryList);
    }

    /**
     * Method to init the adapter and set it to the recycler view
     *
     * @param categories - the categories list to be passed to the adapter
     */
    public void passToCategoriesAdapter(List<Category> categories) {

        // Init adapter
        categoryAdapter = new CategoryAdapter(MainActivity.this, categories, this);

        // Set adapter
        categoriesRV.setAdapter(categoryAdapter);
        categoriesRV.setLayoutManager(new GridLayoutManager(MainActivity.this, 8, GridLayoutManager.VERTICAL, false));
        categoriesRV.setHasFixedSize(true);

    }

    /**
     * The method that will make the API calls to get the news articles
     *
     * @param category the string passed into the method to get the news articles
     */
    public void getNewsArticles(String category) {

        // Clear out any items in the articles list
        articleList.clear();

        // Important strings
        String categoryURL = "https://newsapi.org/v2/top-headlines?country=" +
                Constants.COUNTRY + "&category=" + category + "&apiKey=" + Constants.NEWS_API_KEY;

        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEWS_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Call from my RetrofitAPI
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        // Configure the API call
        Call<News> newsAPICall;

        // Check if a category is passed
        if (category.equals("all")) {
            Toast.makeText(this, "Call for all categories", Toast.LENGTH_SHORT).show();
            newsAPICall = retrofitAPI.getGlobalNews(Constants.GET_ALL_NEWS_URL);
        } else {
            Toast.makeText(this, "Call for the " + category + " category", Toast.LENGTH_SHORT).show();
            newsAPICall = retrofitAPI.getCategoryNews(categoryURL);
        }

        // Making the API call
        newsAPICall.enqueue(new Callback<News>() {
            @Override
            public void onResponse(@NonNull Call<News> call, @NonNull Response<News> response) {

                // When the call returns a successful response
                Toast.makeText(MainActivity.this, "API call successful", Toast.LENGTH_SHORT).show();

                // Toggle view
                toggleView("success");
            }

            @Override
            public void onFailure(@NonNull Call<News> call, @NonNull Throwable t) {

                // If there is an error
                Toast.makeText(MainActivity.this, "API call failed", Toast.LENGTH_SHORT).show();

                // Toggle view
                toggleView("fail");

            }
        });
    }

    /**
     * To hide the progress bar and toggle display based on the callback status
     *
     * @param status the callback status of the API call
     */
    public void toggleView(String status) {

        // Hide the Progress Bar
        trendingLoading.setVisibility(View.GONE);

        if (status.equals("success")) {

            // Show the recycler view
            articlesRV.setVisibility(View.VISIBLE);

        } else {

            // Show the error message
            trendingError.setVisibility(View.VISIBLE);

        }

    }
}