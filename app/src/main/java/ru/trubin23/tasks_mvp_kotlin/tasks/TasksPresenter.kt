package ru.trubin23.tasks_mvp_kotlin.tasks

import ru.trubin23.tasks_mvp_kotlin.data.Task
import ru.trubin23.tasks_mvp_kotlin.data.source.TasksRepository

class TasksPresenter(private val mTasksRepository: TasksRepository,
                     private val mTasksView: TasksContract.View)
    : TasksContract.Presenter {

    init {
        mTasksView.mPresenter = this
    }

    override fun start() {
    }

    private fun loadTasks(){

    }

    override fun addNewTask() {
        mTasksView.showAddTask()
    }

    override fun openTaskDetail(requestedTask: Task) {
        mTasksView.showTaskDetail(requestedTask.mId)
    }

    override fun completeTask(completeTask: Task) {
        mTasksView.showTaskMarkedComplete()
    }

    override fun activateTask(activateTask: Task) {
        mTasksView.showTaskMarkedActivate()
    }
}
