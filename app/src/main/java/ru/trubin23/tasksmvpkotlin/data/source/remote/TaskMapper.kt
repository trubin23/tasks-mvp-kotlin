package ru.trubin23.tasksmvpkotlin.data.source.remote

import ru.trubin23.tasksmvpkotlin.data.Task

internal object TaskMapper {

    fun networkTaskToTask(networkTask: NetworkTask): Task {
        return Task(networkTask.title, networkTask.description, networkTask.id,
                StatusOfTask.integerToBoolean(networkTask.completed))
    }

    fun taskToNetworkTask(task: Task): NetworkTask {
        return NetworkTask(task.mId, task.mTitle, task.mDescription,
                task.mIsCompleted)
    }

    fun networkTaskListToTaskList(networkTaskList: List<NetworkTask>): List<Task> {
        val taskList: MutableList<Task> = ArrayList()

        for (networkTask in networkTaskList) {
            val task = networkTaskToTask(networkTask)
            taskList.add(task)
        }

        return taskList
    }
}