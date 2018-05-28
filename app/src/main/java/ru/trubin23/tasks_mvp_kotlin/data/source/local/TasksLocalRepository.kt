package ru.trubin23.tasks_mvp_kotlin.data.source.local

import ru.trubin23.tasks_mvp_kotlin.data.Task
import ru.trubin23.tasks_mvp_kotlin.data.source.TasksDataSource
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class TasksLocalRepository private constructor(
        private val mTasksDao: TasksDao,
        private val mDiskIO: Executor
) : TasksLocalDataSource {

    override fun getTasks(callback: TasksDataSource.LoadTasksCallback) {
        mDiskIO.execute {
            val tasks = mTasksDao.getTasks()
            if (tasks.isEmpty()) {
                callback.onDataNotAvailable()
            } else {
                callback.onTasksLoaded(tasks)
            }
        }
    }

    override fun getTask(taskId: String, callback: TasksDataSource.GetTaskCallback) {
        mDiskIO.execute {
            val task = mTasksDao.getTaskById(taskId)
            if (task == null) {
                callback.onDataNotAvailable()
            } else {
                callback.onTaskLoaded(task)
            }
        }
    }

    override fun saveTask(task: Task) {
        mDiskIO.execute { mTasksDao.insertTask(task) }
    }

    override fun updateTask(task: Task) {
        mDiskIO.execute { mTasksDao.updateTask(task) }
    }

    override fun deleteTask(taskId: String) {
        mDiskIO.execute { mTasksDao.deleteTaskById(taskId) }
    }

    override fun deleteAllTasks() {
        mDiskIO.execute { mTasksDao.deleteTasks() }
    }

    companion object {
        private var INSTANCE: TasksLocalRepository? = null

        @JvmStatic
        fun getInstance(tasksDao: TasksDao): TasksLocalRepository {
            if (INSTANCE == null) {
                synchronized(TasksLocalRepository::javaClass) {
                    if (INSTANCE == null) {
                        INSTANCE = TasksLocalRepository(tasksDao,
                                Executors.newSingleThreadExecutor())
                    }
                }
            }
            return INSTANCE!!
        }
    }
}