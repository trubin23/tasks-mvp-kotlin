package ru.trubin23.tasks_mvp_kotlin.data.source.local

import ru.trubin23.tasks_mvp_kotlin.data.Task
import ru.trubin23.tasks_mvp_kotlin.data.source.TasksDataSource
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class TasksLocalRepository private constructor(
        private val mTasksDao: TasksDao,
        private val mExecutor: Executor
) : TasksLocalDataSource {

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

    override fun deleteAllTasks() {
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