package ru.trubin23.tasks_mvp_kotlin.data

class Task(var mTitle: String = "", var mDescription: String = "") {

    var isCompleted = false

    val titleForList: String
        get() = if (mTitle.isNotEmpty()) {
            mTitle
        } else {
            mDescription
        }

}