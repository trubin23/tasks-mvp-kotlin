package ru.trubin23.tasks_mvp_kotlin.data.source.cache

class TasksCacheRepository : TasksCacheDataSource {

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