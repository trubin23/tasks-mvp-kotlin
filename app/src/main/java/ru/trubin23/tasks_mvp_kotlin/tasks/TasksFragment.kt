package ru.trubin23.tasks_mvp_kotlin.tasks

import android.support.v4.app.Fragment

class TasksFragment : Fragment(), TasksContract.View {

    override lateinit var presenter: TasksContract.Presenter

}