package ru.trubin23.tasks_mvp_kotlin.data.source.cache

import ru.trubin23.tasks_mvp_kotlin.data.Task

class TasksCacheRepository : TasksCacheDataSource {

    private val mCachedTask: HashMap<String, Task> = LinkedHashMap()

    private var mCacheIsDirty = false

    override fun getTasks(): List<Task>? {
        return if (cacheNotAvailable()){
            null
        } else {
            ArrayList(mCachedTask.values)
        }
    }

    override fun setTasks(tasks: List<Task>) {
        mCachedTask.clear()

        for (task in tasks){
            mCachedTask[task.mId] = task
        }
        mCacheIsDirty = false
    }

    override fun getTaskById(taskId: String): Task? {
        return if (cacheNotAvailable()){
            null
        } else {
            mCachedTask[taskId]
        }
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
        mCacheIsDirty = true
    }

    private fun cacheNotAvailable(): Boolean {
        return mCacheIsDirty || mCachedTask.isEmpty()
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