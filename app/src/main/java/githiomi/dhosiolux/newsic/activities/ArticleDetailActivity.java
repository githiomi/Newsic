package githiomi.dhosiolux.newsic.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import githiomi.dhosiolux.newsic.databinding.ActivityArticleDetailBinding;
import githiomi.dhosiolux.newsic.models.Article;

public class ArticleDetailActivity extends AppCompatActivity {

    // Layout Views
    protected ImageView articleImage;
    protected TextView articleTitle, articleSubTitle, articleContent;
    protected Button externalLinkBtn;

    // Passed object
    Article article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create Bind
        ActivityArticleDetailBinding articleBinding = ActivityArticleDetailBinding.inflate(getLayoutInflater());
        // Set content view
        setContentView(articleBinding.getRoot());

        // Binding views
        articleImage = articleBinding.IVArticleImage;
        articleTitle = articleBinding.TVArticleTitle;
        articleSubTitle = articleBinding.TVArticleSubtitle;
        articleContent = articleBinding.TVArticleContent;
        externalLinkBtn = articleBinding.BTNReadArticle;


        // Get content from intent
        Intent intent = getIntent();
        // Get passed article
        String image = intent.getStringExtra("imageUrl");
        String title = intent.getStringExtra("title");
        String subtitle = intent.getStringExtra("subtitle");
        String content = intent.getStringExtra("content");
        String url = intent.getStringExtra("externalUrl");

        article = new Article(title, subtitle, url, image, content);

        // Populate the views
        populateViews(article);

    }

    public void populateViews(Article article) {

        // Load image
        Picasso.get().load(article.getUrlToImage()).into(articleImage);

        articleTitle.setText(article.getTitle());
        articleSubTitle.setText(article.getDescription());
        articleContent.setText(article.getContent());

        // Add link to button
        externalLinkBtn.setOnClickListener((View view) -> {

            // Start external activity
            Intent externalIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.getUrl()));
            startActivity(externalIntent);

        });

    }
}