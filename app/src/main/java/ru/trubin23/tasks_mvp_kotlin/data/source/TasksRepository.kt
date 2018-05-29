package ru.trubin23.tasks_mvp_kotlin.data.source

import ru.trubin23.tasks_mvp_kotlin.data.Task
import ru.trubin23.tasks_mvp_kotlin.data.source.local.TasksLocalDataSource

class TasksRepository private constructor(
        val mTasksRemoteDataSource: TasksDataSource,
        val mTasksLocalDataSource: TasksLocalDataSource
) : TasksDataSource {

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

        private var INSTANCE: TasksRepository? = null

        @JvmStatic
        fun getInstance(tasksRemoteDataSource: TasksDataSource,
                        tasksLocalDataSource: TasksLocalDataSource): TasksRepository {
            return INSTANCE ?: TasksRepository(tasksRemoteDataSource,
                    tasksLocalDataSource).apply { INSTANCE = this }
        }
    }
}