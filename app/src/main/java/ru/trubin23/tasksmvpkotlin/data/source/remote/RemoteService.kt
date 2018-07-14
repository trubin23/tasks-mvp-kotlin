package ru.trubin23.tasksmvpkotlin.data.source.remote

import retrofit2.Call
import retrofit2.http.*

internal interface RemoteService {

    @GET("/api_mvp_kotlin/tasks")
    fun getTasks(): Call<List<NetworkTask>>

    @GET("/api_mvp_kotlin/tasks/{id}")
    fun getTask(@Path("id") id: String): Call<NetworkTask>

    @POST("/api_mvp_kotlin/tasks")
    fun addTask(@Body task: NetworkTask): Call<NetworkTask>

    @PUT("/api_mvp_kotlin/tasks/{id}")
    fun updateTask(@Path("id") id: String, @Body task: NetworkTask): Call<NetworkTask>

    @PUT("/api_mvp_kotlin/tasks/{id}")
    fun completeTask(@Path("id") id: String, @Body task: StatusOfTask): Call<NetworkTask>

    @DELETE("/api_mvp_kotlin/tasks/{id}")
    fun deleteTask(@Path("id") id: String): Call<NetworkTask>

    @DELETE("/api_mvp_kotlin/tasks/completed")
    fun deleteCompletedTasks(): Call<Int>
}