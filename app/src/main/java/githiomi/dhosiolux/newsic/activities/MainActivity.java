package githiomi.dhosiolux.newsic.activities;

import android.os.Bundle;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import githiomi.dhosiolux.newsic.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // Layout Views
    RecyclerView categoriesRV, articlesRV;
    RelativeLayout trendingLoading;

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
    }
}