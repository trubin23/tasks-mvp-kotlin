package ru.trubin23.tasks_mvp_kotlin.data.source.remote

import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class RetrofitClient {

    private var sRemoteService: RemoteService? = null

    private fun getRemoteService(): RemoteService {
        if (sRemoteService == null) {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            sRemoteService = retrofit.create(RemoteService::class.java)
        }
        return sRemoteService!!
    }

    open fun getTasks(callback: Callback<List<NetworkTask>>) {
        val remoteService = getRemoteService()
        remoteService.getTasks().enqueue(callback)
    }

    fun getTask(taskId: String, callback: Callback<NetworkTask>) {
        val remoteService = getRemoteService()
        remoteService.getTask(taskId).enqueue(callback)
    }

    fun addTask(task: NetworkTask, callback: Callback<NetworkTask>) {
        val remoteService = getRemoteService()
        remoteService.addTask(task).enqueue(callback)
    }

    fun updateTask(task: NetworkTask, callback: Callback<NetworkTask>) {
        val remoteService = getRemoteService()
        remoteService.updateTask(task.id!!, task).enqueue(callback)
    }

    fun completeTask(taskId: String, statusOfTask: StatusOfTask,
                     callback: Callback<NetworkTask>) {
        val remoteService = getRemoteService()
        remoteService.completeTask(taskId, statusOfTask).enqueue(callback)
    }

    fun deleteTask(taskId: String, callback: Callback<NetworkTask>) {
        val remoteService = getRemoteService()
        remoteService.deleteTask(taskId).enqueue(callback)
    }

    fun deleteCompletedTasks(callback: Callback<Int>) {
        val remoteService = getRemoteService()
        remoteService.deleteCompletedTasks().enqueue(callback)
    }

    companion object {
        const val BASE_URL = "https://trubin23.ru"
    }
}