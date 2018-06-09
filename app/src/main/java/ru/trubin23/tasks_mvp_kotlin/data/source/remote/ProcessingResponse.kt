package ru.trubin23.tasks_mvp_kotlin.data.source.remote

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProcessingResponse<T> : Callback<T> {
    override fun onResponse(call: Call<T>?, response: Response<T>) {
        if (response.isSuccessful){
            val body = response.body()
            if (body != null){
                responseBody(body)
            } else {
                dataNotAvailable()
            }
        } else {
            dataNotAvailable()
        }
    }

    override fun onFailure(call: Call<T>?, t: Throwable?) {
        dataNotAvailable()
    }

    fun responseBody(body: T) {

    }

    fun dataNotAvailable() {

    }
}