package com.vladan.color_interview.Repository;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.vladan.color_interview.api.ApiService;
import com.vladan.color_interview.model.ApiResponsePerson;
import com.vladan.color_interview.model.ListIds;
import com.vladan.color_interview.model.Person;
import com.vladan.color_interview.view.ui.MainActivity;


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
    private static ListAndPersonRepository _instance;
    private Context mContext;

    public static ListAndPersonRepository getInstance(ApiService apiService, Context context) {
        if (_instance == null) {
            _instance = new ListAndPersonRepository(apiService, context);
        }
        return _instance;
    }

    public ListAndPersonRepository(ApiService apiService, Context context) {
        this.mApiService = apiService;
        this.mContext = context;
    }

    public MutableLiveData<ListIds> getIdList() {
        final MutableLiveData<ListIds> data = new MutableLiveData<>();
        mApiService.getIdList().enqueue(new Callback<ListIds>() {
            @Override
            public void onResponse(@NotNull Call<ListIds> call, @NotNull Response<ListIds> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    String message = response.message();
                    Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<ListIds> call, @NotNull Throwable t) {
                data.setValue(null);
                String errorMessage = t.getMessage();
                Toast.makeText(mContext, errorMessage, Toast.LENGTH_LONG).show();
            }
        });
        return data;
    }

    public LiveData<Person> getPersonDetails(String id) {
        final MutableLiveData<Person> personMutableLiveData = new MutableLiveData<>();
        mApiService.getPersonDetails(id).enqueue(new Callback<ApiResponsePerson>() {
            @Override
            public void onResponse(@NotNull Call<ApiResponsePerson> call, @NotNull Response<ApiResponsePerson> response) {
                if (response.isSuccessful()) {
                    simulateDelay();
                    assert response.body() != null;
                    personMutableLiveData.setValue(response.body().data);
                } else {
                    String message = response.message();
                    Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<ApiResponsePerson> call, @NotNull Throwable t) {
                personMutableLiveData.setValue(null);
                String errorMessage = t.getMessage();
                Toast.makeText(mContext, errorMessage, Toast.LENGTH_LONG).show();
            }
        });
        return personMutableLiveData;
    }

    private void simulateDelay() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
