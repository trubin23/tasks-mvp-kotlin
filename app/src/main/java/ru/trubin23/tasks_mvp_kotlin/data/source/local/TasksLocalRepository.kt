package ru.trubin23.tasks_mvp_kotlin.data.source.local

import ru.trubin23.tasks_mvp_kotlin.data.source.TasksDataSource

class TasksLocalRepository : TasksLocalDataSource {
    override fun getTasks(callback: TasksDataSource.LoadTasksCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTask(taskId: String, callback: TasksDataSource.GetTaskCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteAllTasks() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}