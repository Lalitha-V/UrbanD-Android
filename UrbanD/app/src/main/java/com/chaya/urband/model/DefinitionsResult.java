package com.chaya.urband.model;

import android.util.Log;

import com.chaya.urband.networking.Api;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DefinitionsResult extends BaseObservable {

    @SerializedName("list")
    @Expose
    private List<Definition> definitionList = new ArrayList<>();


    private MutableLiveData<List<Definition>> definitions = new MutableLiveData<>();

    public MutableLiveData<List<Definition>> getDefinitions() {
        return definitions;
    }

    public void fetchList(String term) {
        Callback<DefinitionsResult> callback = new Callback<DefinitionsResult>() {
            @Override
            public void onResponse(Call<DefinitionsResult> call, Response<DefinitionsResult> response) {
                DefinitionsResult body = response.body();
                definitions.setValue(body.definitionList);
            }

            @Override
            public void onFailure(Call<DefinitionsResult> call, Throwable t) {
                Log.e("Test", t.getMessage(), t);
            }
        };

        Api.getApi().getDefinitions(term).enqueue(callback);
    }
}
