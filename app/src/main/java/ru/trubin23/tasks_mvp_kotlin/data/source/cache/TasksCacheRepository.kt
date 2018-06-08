package ru.trubin23.tasks_mvp_kotlin.data.source.cache

import ru.trubin23.tasks_mvp_kotlin.data.Task

class TasksCacheRepository : TasksCacheDataSource {

    override fun getTasks(): List<Task> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setTasks(tasks: List<Task>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTaskById(taskId: String): Task {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addTask(task: Task) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeTask(taskId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun completedTask(taskId: String, completed: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearCompletedTask() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun irrelevantState() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        private var INSTANCE: TasksCacheRepository? = null

        @JvmStatic
        fun getInstance(): TasksCacheRepository {
            if (INSTANCE == null) {
                synchronized(TasksCacheRepository::javaClass) {
                    if (INSTANCE == null) {
                        INSTANCE = TasksCacheRepository()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}