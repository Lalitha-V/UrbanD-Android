package com.chaya.urband.networking;

import com.chaya.urband.model.DefinitionsResult;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public class Api {

    private static ApiInterface api;
    private static final String BASE_URL = "https://mashape-community-urban-dictionary.p.rapidapi.com";

    public static ApiInterface getApi() {
        if (api == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .build();


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            api = retrofit.create(ApiInterface.class);
        }
        return api;
    }

    public interface ApiInterface {

        @Headers({
                "X-RapidAPI-Host: mashape-community-urban-dictionary.p.rapidapi.com",
                "X-RapidAPI-Key: 6345ec206dmshe0f3bda668eed46p17678bjsnd64aa83f432b"
        })
        @GET("/define")
        Call<DefinitionsResult> getDefinitions(@Query("term") String term);

    }
}