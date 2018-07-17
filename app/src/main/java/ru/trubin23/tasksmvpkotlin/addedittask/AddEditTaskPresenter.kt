package ru.trubin23.tasksmvpkotlin.addedittask

import ru.trubin23.tasksmvpkotlin.data.Task
import ru.trubin23.tasksmvpkotlin.data.source.TasksDataSource
import ru.trubin23.tasksmvpkotlin.data.source.TasksRepository

class AddEditTaskPresenter(
        private val mTaskId: String?,
        private val mTasksRepository: TasksRepository,
        private val mAddEditTaskView: AddEditTaskContract.View)
    : AddEditTaskContract.Presenter {

    init {
        mAddEditTaskView.mPresenter = this
    }

    override fun start() {
        mTaskId?.apply {
            mTasksRepository.getTask(this, object : TasksDataSource.GetTaskCallback {
                override fun onTaskLoaded(task: Task) {
                    mAddEditTaskView.setTitle(task.mTitle)
                    mAddEditTaskView.setDescription(task.mDescription)
                }

                override fun onDataNotAvailable() {
                    mAddEditTaskView.showEmptyTaskError()
                }
            })
        }
    }

    override fun saveTask(title: String, description: String) {
        if (mTaskId == null){
            createTask(title, description)
        } else {
            updateTask(title, description)
        }
    }

    private fun createTask(title: String, description: String) {
        val newTask = Task(title, description)
        if (newTask.isEmpty){
            mAddEditTaskView.showEmptyTaskError()
        } else {
            mTasksRepository.saveTask(newTask)
            mAddEditTaskView.showTaskList()
        }
    }

    private fun updateTask(title: String, description: String) {
        if (mTaskId != null){
            mTasksRepository.saveTask(Task(title, description, mTaskId))
        }
    }
}