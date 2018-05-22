package ru.trubin23.tasks_mvp_kotlin.data

import java.util.*

class Task(var mTitle: String = "", var mDescription: String = "",
           var mId: String = UUID.randomUUID().toString()) {

    var isCompleted = false

    val titleForList: String
        get() = if (mTitle.isNotEmpty()) {
            mTitle
        } else {
            mDescription
        }

}