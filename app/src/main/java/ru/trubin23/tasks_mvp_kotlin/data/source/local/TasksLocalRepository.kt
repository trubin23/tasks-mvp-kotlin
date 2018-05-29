package ru.trubin23.tasks_mvp_kotlin.data.source.local

import ru.trubin23.tasks_mvp_kotlin.data.Task
import ru.trubin23.tasks_mvp_kotlin.data.source.TasksDataSource
import ru.trubin23.tasks_mvp_kotlin.util.AppExecutors
import java.util.concurrent.Executors

class TasksLocalRepository private constructor(
        private val mAppExecutors: AppExecutors,
        private val mTasksDao: TasksDao
) : TasksLocalDataSource {

    override fun getTasks(callback: TasksDataSource.LoadTasksCallback) {
        mAppExecutors.diskIO.execute {
            val tasks = mTasksDao.getTasks()
            if (tasks.isEmpty()) {
                callback.onDataNotAvailable()
            } else {
                callback.onTasksLoaded(tasks)
            }
        }
    }

    override fun getTask(taskId: String, callback: TasksDataSource.GetTaskCallback) {
        mAppExecutors.diskIO.execute {
            val task = mTasksDao.getTaskById(taskId)
            if (task == null) {
                callback.onDataNotAvailable()
            } else {
                callback.onTaskLoaded(task)
            }
        }
    }

    override fun saveTask(task: Task) {
        mAppExecutors.diskIO.execute { mTasksDao.insertTask(task) }
    }

    override fun updateTask(task: Task) {
        mAppExecutors.diskIO.execute { mTasksDao.updateTask(task) }
    }

    override fun deleteTask(taskId: String) {
        mAppExecutors.diskIO.execute { mTasksDao.deleteTaskById(taskId) }
    }

    override fun deleteAllTasks() {
        mAppExecutors.diskIO.execute { mTasksDao.deleteTasks() }
    }

    companion object {
        private var INSTANCE: TasksLocalRepository? = null

        @JvmStatic
        fun getInstance(appExecutors: AppExecutors,
                        tasksDao: TasksDao): TasksLocalRepository {
            if (INSTANCE == null) {
                synchronized(TasksLocalRepository::javaClass) {
                    if (INSTANCE == null) {
                        INSTANCE = TasksLocalRepository(appExecutors, tasksDao)
                    }
                }
            }
            return INSTANCE!!
        }
    }
}