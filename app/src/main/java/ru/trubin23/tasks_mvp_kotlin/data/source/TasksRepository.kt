package ru.trubin23.tasks_mvp_kotlin.data.source

import ru.trubin23.tasks_mvp_kotlin.data.source.local.TasksLocalDataSource

class TasksRepository private constructor(
        val mTasksRemoteDataSource: TasksDataSource,
        val mTasksLocalDataSource: TasksLocalDataSource
) : TasksDataSource {

    override fun getTasks(callback: TasksDataSource.LoadTasksCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTask(taskId: String, callback: TasksDataSource.GetTaskCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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