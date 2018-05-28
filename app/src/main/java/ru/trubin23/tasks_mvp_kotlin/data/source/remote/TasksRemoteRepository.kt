package ru.trubin23.tasks_mvp_kotlin.data.source.remote

import ru.trubin23.tasks_mvp_kotlin.data.Task
import ru.trubin23.tasks_mvp_kotlin.data.source.TasksDataSource

class TasksRemoteRepository private constructor() : TasksDataSource {

    override fun getTasks(callback: TasksDataSource.LoadTasksCallback) {
    }

    override fun getTask(taskId: String, callback: TasksDataSource.GetTaskCallback) {
    }

    override fun saveTask(task: Task) {
    }

    override fun updateTask(task: Task) {
    }

    override fun deleteTask(taskId: String) {
    }

    companion object {
        private var INSTANCE: TasksRemoteRepository? = null

        @JvmStatic
        fun getInstance(): TasksRemoteRepository {
            if (INSTANCE == null) {
                synchronized(TasksRemoteRepository::javaClass) {
                    if (INSTANCE == null) {
                        INSTANCE = TasksRemoteRepository()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}