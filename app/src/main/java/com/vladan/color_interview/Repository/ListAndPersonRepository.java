package com.vladan.color_interview.Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.vladan.color_interview.api.ApiService;
import com.vladan.color_interview.model.ListIds;
import com.vladan.color_interview.model.Person;

import org.jetbrains.annotations.NotNull;

import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vladan on 8/29/2020
 */
@Singleton
public class ListAndPersonRepository {
    private ApiService mApiService;

    public ListAndPersonRepository(ApiService apiService) {
        this.mApiService = apiService;
    }

    public MutableLiveData<ListIds> getIdList() {
        final MutableLiveData<ListIds> data = new MutableLiveData<>();
        mApiService.getIdList().enqueue(new Callback<ListIds>() {
            @Override
            public void onResponse(@NotNull Call<ListIds> call, @NotNull Response<ListIds> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<ListIds> call, @NotNull Throwable t) {
                data.setValue(null);

            }
        });
        return data;
    }

    public LiveData<Person> getPersonDetails(){
        final MutableLiveData<Person> data = new MutableLiveData<>();
        mApiService.getPersonDetails().enqueue(new Callback<Person>() {
            @Override
            public void onResponse(@NotNull Call<Person> call, @NotNull Response<Person> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NotNull Call<Person> call, @NotNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
