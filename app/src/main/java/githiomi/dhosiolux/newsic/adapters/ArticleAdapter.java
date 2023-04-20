package githiomi.dhosiolux.newsic.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;

import githiomi.dhosiolux.newsic.R;
import githiomi.dhosiolux.newsic.activities.ArticleDetailActivity;
import githiomi.dhosiolux.newsic.models.Article;
import githiomi.dhosiolux.newsic.models.Constants;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    // Adapter Variables
    List<Article> articlesList;
    Context context;

    // Adapter constructor
    public ArticleAdapter(List<Article> articlesList, Context context) {
        this.articlesList = articlesList;
        this.context = context;
    }

    // Adapter Methods
    @NonNull
    @Override
    public ArticleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_trending, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleAdapter.ViewHolder holder, int position) {

        // Get the specific article at the specified position
        Article article = articlesList.get(position);

        // Populate the views
        Picasso.get().load(article.getUrlToImage()).into(holder.articleImage);

        holder.articleTitle.setText(article.getTitle());
        holder.articleSubtitle.setText(article.getDescription());

        // Add an on click listener to the item view
        holder.itemView.setOnClickListener((View view) -> {

            // Handle the click event
            Intent articleDetailIntent = new Intent(context, ArticleDetailActivity.class);

            // Pass the selected article to a new intent
            articleDetailIntent.putExtra(Constants.ARTICLE_WRAP, Parcels.wrap(article));

        });

    }

    @Override
    public int getItemCount() {
        return articlesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        // Layout Views
        TextView articleTitle, articleSubtitle;
        ImageView articleImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Binding the views
            articleImage = (ImageView) itemView.findViewById(R.id.IV_trending);
            articleTitle = (TextView) itemView.findViewById(R.id.TV_trendingItemTitle);
            articleSubtitle = (TextView) itemView.findViewById(R.id.TV_trendingItemSubTitle);

        }
    }
}
