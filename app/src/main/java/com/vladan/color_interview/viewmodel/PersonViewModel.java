package com.vladan.color_interview.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.vladan.color_interview.Repository.ListAndPersonRepository;
import com.vladan.color_interview.api.RetrofitService;
import com.vladan.color_interview.model.ApiResponsePerson;

import java.lang.reflect.Type;

/**
 * Created by vladan on 8/30/2020
 */
public class PersonViewModel extends ViewModel {
    private final LiveData<ApiResponsePerson> mPersonLiveData;
    Gson mGson = new GsonBuilder()
            .registerTypeAdapter(ApiResponsePerson.class, new CustomDeserializer<ApiResponsePerson>()).create();

    public PersonViewModel(Context context, String id) {
        ListAndPersonRepository repository = ListAndPersonRepository.getInstance(RetrofitService.create(context, mGson), context);
        mPersonLiveData = repository.getPersonDetails(id);
    }

    public LiveData<ApiResponsePerson> getPersonLiveData() {
        return mPersonLiveData;
    }

    static class CustomDeserializer<T> implements JsonDeserializer<T> {
        @Override
        public T deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
                throws JsonParseException {
            JsonElement content = je.getAsJsonObject();

            return new Gson().fromJson(content, type);

        }
    }
}
