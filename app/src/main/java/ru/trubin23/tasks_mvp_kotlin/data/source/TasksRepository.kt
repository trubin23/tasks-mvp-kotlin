package ru.trubin23.tasks_mvp_kotlin.data.source

class TasksRepository : TasksDataSource{

    override fun getTasks(callback: TasksDataSource.LoadTasksCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTask(taskId: String, callback: TasksDataSource.GetTaskCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {

        private var INSTANCE: TasksRepository? = null

        @JvmStatic fun getInstance() : TasksRepository{
            return INSTANCE ?: TasksRepository().apply { INSTANCE = this }
        }
    }
}