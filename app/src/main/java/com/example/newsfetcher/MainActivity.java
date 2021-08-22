package com.example.newsfetcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CategoryRVAdapter.CategoryClickInterface {
    //0764e4ccebd146b0b966d447bfade5fb
    private RecyclerView newsRV,categoryRV ;
    private ProgressBar loadingPB ;
    private ArrayList<Articles> articlesArrayList ;
    private ArrayList<CategoryRVModal> categoryRVModalArrayList ;
    private NewsRVAdapter newsRVAdapter ;
    private CategoryRVAdapter categoryRVAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsRV = findViewById(R.id.idRVNews);
        categoryRV = findViewById(R.id.idRVCategories);
        loadingPB = findViewById(R.id.idPBLoading);
        articlesArrayList = new ArrayList<>();
        categoryRVModalArrayList = new ArrayList<>() ;
        newsRVAdapter = new NewsRVAdapter(articlesArrayList,this);
        categoryRVAdapter = new CategoryRVAdapter(categoryRVModalArrayList,this, this);
        newsRV.setLayoutManager(new LinearLayoutManager(this));
        newsRV.setAdapter(newsRVAdapter);
        categoryRV.setAdapter(categoryRVAdapter);
        getCategories();
        getNews("All");
        newsRVAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCategoryClick(int position) {
        String category = categoryRVModalArrayList.get(position).getCategory();
        getNews(category);
    }
    private void getCategories()
    {
        categoryRVModalArrayList.add(new CategoryRVModal("All",R.drawable.daily_updates));
        categoryRVModalArrayList.add(new CategoryRVModal("Technology",R.drawable.tecnhology));
        categoryRVModalArrayList.add(new CategoryRVModal("Science",R.drawable.science));
        categoryRVModalArrayList.add(new CategoryRVModal("Sports",R.drawable.sports));
        categoryRVModalArrayList.add(new CategoryRVModal("General",R.drawable.general));
        categoryRVModalArrayList.add(new CategoryRVModal("Business",R.drawable.business));
        categoryRVModalArrayList.add(new CategoryRVModal("Entertainment",R.drawable.entertainment));
        categoryRVModalArrayList.add(new CategoryRVModal("Health",R.drawable.health));
        categoryRVAdapter.notifyDataSetChanged();
    }
    private void getNews(String category)
    {
        loadingPB.setVisibility(View.VISIBLE);
        articlesArrayList.clear();
        String categoryUrl = "https://newsapi.org/v2/top-headlines?country=us&apiKey=36ee9b7627ac46f18f37469419efa573";
        String url = "https://newsapi.org/v2/top-headlines?country=us&apiKey=0764e4ccebd146b0b966d447bfade5fb" ;
        String BASE_URL="https://newsapi.org/v2/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build() ;
        RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);
        Call<NewsModal> call ;
        if(category.equals("All"))
        {
            call = retrofitApi.getAllNews("in","0764e4ccebd146b0b966d447bfade5fb");
        }
        else
        {
            call = retrofitApi.getNewsByCategory(category,"in","0764e4ccebd146b0b966d447bfade5fb");
        }
        call.enqueue(new Callback<NewsModal>() {
            @Override
            public void onResponse(Call<NewsModal> call, Response<NewsModal> response) {
                NewsModal newsModal = response.body();
                loadingPB.setVisibility(View.GONE);
                ArrayList<Articles> articles = newsModal.getArticles();
                for(int i = 0 ; i < articles.size() ; i++)
                {
                    articlesArrayList.add(new Articles(articles.get(i).getTitle() , articles.get(i).getDescription(),articles.get(i).getUrlToImage(),articles.get(i).getUrl(),articles.get(i).getContent()));
                }
                NewsRVAdapter newsRVAdapter = new NewsRVAdapter(articlesArrayList,MainActivity.this);
                newsRV.setAdapter(newsRVAdapter);
            }

            @Override
            public void onFailure(Call<NewsModal> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Fail to get news",Toast.LENGTH_SHORT).show();
            }
        });
    }

}