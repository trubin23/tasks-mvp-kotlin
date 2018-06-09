package ru.trubin23.tasks_mvp_kotlin.data.source.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

internal class StatusOfTask(completed: Boolean) {

    @SerializedName("completed")
    @Expose
    var mCompleted: Int? = null

    init {
        mCompleted = booleanToInteger(completed)
    }

    companion object {

        fun booleanToInteger(completed: Boolean): Int {
            return if (completed) 1 else 0
        }

        fun integerToBoolean(completed: Int?): Boolean {
            return completed != null && completed != 0
        }
    }
}