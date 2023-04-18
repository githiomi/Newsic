package githiomi.dhosiolux.newsic.interfaces;

import githiomi.dhosiolux.newsic.models.News;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

// This is the class that uses the Retrofit library to fetch data from the URLs
public interface RetrofitAPI {

    @GET
    Call<News> getGlobalNews(@Url String globalUrl);

    @GET
    Call<News> getCategoryNews(@Url String categoryUrl);

}
