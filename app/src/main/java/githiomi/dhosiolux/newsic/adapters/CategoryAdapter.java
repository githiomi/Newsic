package githiomi.dhosiolux.newsic.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import githiomi.dhosiolux.newsic.R;
import githiomi.dhosiolux.newsic.models.Category;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    // Adapter Variables
    OnCategoryClickInterface categoryClickInterface;
    List<Category> categoriesList;
    Context context;

    // Constructor
    public CategoryAdapter(OnCategoryClickInterface onCategoryClickInterface, List<Category> categoriesList, Context context) {
        this.categoryClickInterface = onCategoryClickInterface;
        this.categoriesList = categoriesList;
        this.context = context;
    }

    // Adapter Methods
    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {

        // Get the specific category at the given position
        Category category = categoriesList.get(position);

        // Populate the view
        holder.categoryName.setText(category.getCategoryName());

        Picasso.get().load(category.getCategoryImageUrl()).into(holder.categoryImage);

    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    // Interface to handle on click inside main activity
    public interface OnCategoryClickInterface {

        void onCategoryClick(int categoryPosition);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        // Layout views
        TextView categoryName;
        ImageView categoryImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Binding Views
            categoryName = (TextView) itemView.findViewById(R.id.TV_categoryName);
            categoryImage = (ImageView) itemView.findViewById(R.id.IV_categoryBG);

        }
    }
}
