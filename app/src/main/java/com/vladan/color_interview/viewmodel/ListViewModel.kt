package com.vladan.color_interview.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vladan.color_interview.Repository.ListAndPersonRepository.Companion.getInstance
import com.vladan.color_interview.api.RetrofitService.create
import com.vladan.color_interview.model.ListIds

/**
 * Created by vladan on 8/29/2020
 */
class ListViewModel(context: Context?) : ViewModel() {
    val listIdsLiveData: LiveData<ListIds?>

    init {
        val repository = getInstance(create(context, null), context)
        listIdsLiveData = repository!!.idList
    }
}