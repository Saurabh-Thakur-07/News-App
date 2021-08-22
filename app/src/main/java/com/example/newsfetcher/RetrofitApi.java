package com.example.newsfetcher;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitApi {

    @GET("top-headlines")
    Call<NewsModal> getAllNews(@Query("country") String countryId,@Query("apiKey") String apiKey);

    @GET("top-headlines")
    Call<NewsModal> getNewsByCategory(@Query("category") String category , @Query("country") String country ,
                                      @Query("apiKey") String apiKey);
}
