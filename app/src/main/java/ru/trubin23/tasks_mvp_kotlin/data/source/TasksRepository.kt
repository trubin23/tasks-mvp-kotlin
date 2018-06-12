package ru.trubin23.tasks_mvp_kotlin.data.source

import ru.trubin23.tasks_mvp_kotlin.data.Task
import ru.trubin23.tasks_mvp_kotlin.data.source.cache.TasksCacheDataSource
import ru.trubin23.tasks_mvp_kotlin.data.source.local.TasksLocalDataSource

class TasksRepository private constructor(
        val mTasksRemoteDataSource: TasksDataSource,
        val mTasksLocalDataSource: TasksLocalDataSource,
        val mTasksCacheDataSource: TasksCacheDataSource
) : TasksDataSource {

    var mCacheTask: LinkedHashMap<String, Task> = LinkedHashMap()

    var mCacheIsDirty = false

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

    override fun completedTask(taskId: String, completed: Boolean) {
    }

    override fun clearCompletedTasks() {
    }

    companion object {

        private var INSTANCE: TasksRepository? = null

        @JvmStatic
        fun getInstance(tasksRemoteDataSource: TasksDataSource,
                        tasksLocalDataSource: TasksLocalDataSource,
                        tasksCacheDataSource: TasksCacheDataSource): TasksRepository {
            return INSTANCE ?: TasksRepository(tasksRemoteDataSource,
                    tasksLocalDataSource, tasksCacheDataSource).apply { INSTANCE = this }
        }
    }
}