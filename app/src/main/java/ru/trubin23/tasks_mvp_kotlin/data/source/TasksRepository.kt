package ru.trubin23.tasks_mvp_kotlin.data.source

import ru.trubin23.tasks_mvp_kotlin.data.Task
import ru.trubin23.tasks_mvp_kotlin.data.source.cache.TasksCacheDataSource
import ru.trubin23.tasks_mvp_kotlin.data.source.local.TasksLocalDataSource
import android.support.annotation.NonNull



class TasksRepository private constructor(
        val mTasksRemoteDataSource: TasksDataSource,
        val mTasksLocalDataSource: TasksLocalDataSource,
        val mTasksCacheDataSource: TasksCacheDataSource
) : TasksMainDataSource {

    private var mForceRefresh: Boolean = false

    override fun getTasks(callback: TasksDataSource.LoadTasksCallback) {
        val tasks = mTasksCacheDataSource.getTasks()
        if (tasks != null) {
            callback.onTasksLoaded(tasks)
            return
        }

        if (mForceRefresh) {
            getTasksFromRemoteDataSource(callback, true)
        } else {
            getTasksFromLocalDataSource(callback, true)
        }
    }

    private fun getTasksFromLocalDataSource(callback: TasksDataSource.LoadTasksCallback,
                                            handleErrors: Boolean) {
        mTasksLocalDataSource.getTasks(object : TasksDataSource.LoadTasksCallback {
            override fun onTasksLoaded(tasks: List<Task>) {
                mTasksCacheDataSource.setTasks(tasks)
                callback.onTasksLoaded(tasks)
            }

            override fun onDataNotAvailable() {
                if (handleErrors) {
                    getTasksFromRemoteDataSource(callback, false)
                } else {
                    callback.onDataNotAvailable()
                }
            }
        })
    }

    private fun getTasksFromRemoteDataSource(callback: TasksDataSource.LoadTasksCallback,
                                             handleErrors: Boolean) {
        mTasksRemoteDataSource.getTasks(object : TasksDataSource.LoadTasksCallback {
            override fun onTasksLoaded(tasks: List<Task>) {
                mTasksCacheDataSource.setTasks(tasks)
                mTasksLocalDataSource.setTasks(tasks)
                mForceRefresh = false
                callback.onTasksLoaded(tasks)
            }

            override fun onDataNotAvailable() {
                if (handleErrors) {
                    getTasksFromLocalDataSource(callback, false)
                } else {
                    callback.onDataNotAvailable()
                }
            }
        })
    }

    override fun getTask(taskId: String, callback: TasksDataSource.GetTaskCallback) {
    }

    override fun saveTask(task: Task) {
        mTasksRemoteDataSource.saveTask(task)
        mTasksLocalDataSource.saveTask(task)
        mTasksCacheDataSource.addTask(task)
    }

    override fun updateTask(task: Task) {
        mTasksRemoteDataSource.updateTask(task)
        mTasksLocalDataSource.updateTask(task)
        mTasksCacheDataSource.addTask(task)
    }

    override fun deleteTask(taskId: String) {
        mTasksRemoteDataSource.deleteTask(taskId)
        mTasksLocalDataSource.deleteTask(taskId)
        mTasksCacheDataSource.removeTask(taskId)
    }

    override fun completedTask(taskId: String, completed: Boolean) {
        mTasksRemoteDataSource.completedTask(taskId, completed)
        mTasksLocalDataSource.completedTask(taskId, completed)
        mTasksCacheDataSource.completedTask(taskId, completed)
    }

    override fun clearCompletedTasks() {
        mTasksRemoteDataSource.clearCompletedTasks()
        mTasksLocalDataSource.clearCompletedTasks()
        mTasksCacheDataSource.clearCompletedTasks()
    }

    override fun refreshTasks() {
        mTasksCacheDataSource.irrelevantState()
        mForceRefresh = true
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