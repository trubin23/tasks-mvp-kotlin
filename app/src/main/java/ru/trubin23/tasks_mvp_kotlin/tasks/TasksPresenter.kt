package ru.trubin23.tasks_mvp_kotlin.tasks

import ru.trubin23.tasks_mvp_kotlin.data.Task
import ru.trubin23.tasks_mvp_kotlin.data.source.TasksDataSource
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
        mTasksRepository.getTasks(object : TasksDataSource.LoadTasksCallback{
            override fun onTasksLoaded(tasks: List<Task>) {
                val tasksToShow = ArrayList<Task>()

                //TODO implement

                showTasks(tasksToShow)
            }

            override fun onDataNotAvailable() {
                mTasksView.showLoadingTasksError()
            }
        })
    }

    private fun showTasks(tasks: List<Task>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
