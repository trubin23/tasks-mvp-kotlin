package ru.trubin23.tasks_mvp_kotlin.taskdetail

import android.support.v4.app.Fragment

class TaskDetailFragment() : Fragment(), TaskDetailContract.View {

    override lateinit var mPresenter: TaskDetailContract.Presenter
}