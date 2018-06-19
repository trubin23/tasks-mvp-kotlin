package ru.trubin23.tasks_mvp_kotlin.addedittask

import android.support.v4.app.Fragment

class AddEditTaskFragment : Fragment(), AddEditTaskContract.View {

    override lateinit var mPresenter: AddEditTaskContract.Presenter
}