package com.vladan.color_interview.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.*
import com.vladan.color_interview.Repository.ListAndPersonRepository.Companion.getInstance
import com.vladan.color_interview.api.RetrofitService.create
import com.vladan.color_interview.model.Person
import java.lang.reflect.Type

/**
 * Created by vladan on 8/30/2020
 */
class PersonViewModel(context: Context?, id: String?) : ViewModel() {
    var personLiveData: MutableLiveData<Person?>? = null
    var mGson =
        GsonBuilder().registerTypeAdapter(Person::class.java, CustomDeserializer<Any>()).create()

    internal class CustomDeserializer<T> : JsonDeserializer<T> {
        @Throws(JsonParseException::class)
        override fun deserialize(je: JsonElement, type: Type, jdc: JsonDeserializationContext): T {
            val content = je.asJsonObject["data"]
            return Gson().fromJson(content, type)
        }
    }

    init {
        val repository = getInstance(create(context, mGson), context)
        personLiveData = repository?.getPersonDetails(id = id)
    }
}