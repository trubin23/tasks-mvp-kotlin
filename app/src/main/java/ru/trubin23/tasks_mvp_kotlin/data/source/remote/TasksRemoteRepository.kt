package ru.trubin23.tasks_mvp_kotlin.data.source.remote

import ru.trubin23.tasks_mvp_kotlin.data.source.TasksDataSource

class TasksRemoteRepository: TasksDataSource{
    override fun getTasks(callback: TasksDataSource.LoadTasksCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTask(taskId: String, callback: TasksDataSource.GetTaskCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}