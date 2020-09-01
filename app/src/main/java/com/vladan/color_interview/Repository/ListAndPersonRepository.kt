package com.vladan.color_interview.Repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vladan.color_interview.api.ApiService
import com.vladan.color_interview.model.ApiResponsePerson
import com.vladan.color_interview.model.ListIds
import com.vladan.color_interview.model.Person
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Singleton

/**
 * Created by vladan on 8/29/2020
 */
@Singleton
class ListAndPersonRepository(private val mApiService: ApiService, private val mContext: Context?) {
    val idList: MutableLiveData<ListIds?>
        get() {
            val data = MutableLiveData<ListIds?>()
            mApiService.idList!!.enqueue(object : Callback<ListIds?> {
                override fun onResponse(call: Call<ListIds?>, response: Response<ListIds?>) {
                    if (response.isSuccessful) {
                        data.setValue(response.body())
                    }
                    else {
                        val message = response.message()
                        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<ListIds?>, t: Throwable) {
                    data.value = null
                    val errorMessage = t.message
                    Toast.makeText(mContext, errorMessage, Toast.LENGTH_LONG).show()
                }
            })
            return data
        }

    fun getPersonDetails(id: String?): MutableLiveData<Person?> {
        var personMutableLiveData = MutableLiveData<Person?>()
        mApiService.getPersonDetails(id)!!.enqueue(object : Callback<ApiResponsePerson?> {
            override fun onResponse(
                call: Call<ApiResponsePerson?>, response: Response<ApiResponsePerson?>
            ) {
                if (response.isSuccessful) {
                    simulateDelay()
                    assert(response.body() != null)
                    personMutableLiveData.setValue(response.body()!!.data)
                }
                else {
                    val message = response.message()
                    Toast.makeText(mContext, message, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ApiResponsePerson?>, t: Throwable) {
                personMutableLiveData.value = null
                val errorMessage = t.message
                Toast.makeText(mContext, errorMessage, Toast.LENGTH_LONG).show()
            }
        })
        return personMutableLiveData
    }

    private fun simulateDelay() {
        try {
            Thread.sleep(100)
        }
        catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    companion object {
        private var _instance: ListAndPersonRepository? = null
        @JvmStatic
        fun getInstance(apiService: ApiService, context: Context?): ListAndPersonRepository? {
            if (_instance == null) {
                _instance = ListAndPersonRepository(apiService, context)
            }
            return _instance
        }
    }
}