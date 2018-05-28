package ru.trubin23.tasks_mvp_kotlin.data.source.local

import ru.trubin23.tasks_mvp_kotlin.data.source.TasksDataSource

class TasksLocalRepository private constructor(
        private val mTasksDao: TasksDao
) : TasksLocalDataSource {

    override fun getTasks(callback: TasksDataSource.LoadTasksCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTask(taskId: String, callback: TasksDataSource.GetTaskCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteAllTasks() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        private var INSTANCE: TasksLocalRepository? = null

        @JvmStatic
        fun getInstance(tasksDao: TasksDao): TasksLocalRepository {
            if (INSTANCE == null) {
                synchronized(TasksLocalRepository::javaClass) {
                    if (INSTANCE == null) {
                        INSTANCE = TasksLocalRepository(tasksDao)
                    }
                }
            }
            return INSTANCE!!
        }
    }
}