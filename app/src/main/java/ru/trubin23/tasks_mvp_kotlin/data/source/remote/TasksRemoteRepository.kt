package ru.trubin23.tasks_mvp_kotlin.data.source.remote

import ru.trubin23.tasks_mvp_kotlin.data.Task
import ru.trubin23.tasks_mvp_kotlin.data.source.TasksDataSource
import ru.trubin23.tasks_mvp_kotlin.util.AppExecutors


class TasksRemoteRepository private constructor(
        private val appExecutors: AppExecutors
) : TasksDataSource {

    override fun getTasks(callback: TasksDataSource.LoadTasksCallback) {
        appExecutors.networkIO.execute({
//            RetrofitClient.getTasks(
//                    object : ProcessingResponse<List<NetworkTask>>() {
//                        override fun responseBody(body: List<NetworkTask>) {
//                            val tasks = TaskMapper.networkTaskListToTaskList(body)
//                            callback.onTasksLoaded(tasks)
//                        }
//
//                        override fun dataNotAvailable() {
//                            callback.onDataNotAvailable()
//                        }
//                    })
        })
    }

    override fun getTask(taskId: String, callback: TasksDataSource.GetTaskCallback) {
    }

    override fun saveTask(task: Task) {
    }

    override fun updateTask(task: Task) {
    }

    override fun deleteTask(taskId: String) {
    }

    override fun completedTask(completeTask: Task) {
    }

    override fun activateTask(activateTask: Task) {
    }

    companion object {
        private var INSTANCE: TasksRemoteRepository? = null

        @JvmStatic
        fun getInstance(appExecutors: AppExecutors): TasksRemoteRepository {
            if (INSTANCE == null) {
                synchronized(TasksRemoteRepository::javaClass) {
                    if (INSTANCE == null) {
                        INSTANCE = TasksRemoteRepository(appExecutors)
                    }
                }
            }
            return INSTANCE!!
        }
    }
}