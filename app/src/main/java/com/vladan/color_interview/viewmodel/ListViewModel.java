package com.vladan.color_interview.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.vladan.color_interview.Repository.ListAndPersonRepository;
import com.vladan.color_interview.api.RetrofitService;
import com.vladan.color_interview.model.ListIds;

/**
 * Created by vladan on 8/29/2020
 */
public class ListViewModel extends ViewModel {

    private final LiveData<ListIds> mListIdsLiveData;


    public ListViewModel(Context context){
        ListAndPersonRepository repository = new ListAndPersonRepository(RetrofitService.create(context));
        mListIdsLiveData = repository.getIdList();
    }

   public LiveData<ListIds> getListIdsLiveData(){
        return mListIdsLiveData;
   }

}
