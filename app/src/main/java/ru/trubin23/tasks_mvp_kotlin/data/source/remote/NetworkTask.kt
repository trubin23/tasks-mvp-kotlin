package ru.trubin23.tasks_mvp_kotlin.data.source.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NetworkTask(@field:SerializedName("id")
                           @field:Expose
                           var id: String?,
                           @field:SerializedName("title")
                           @field:Expose
                           var title: String?,
                           @field:SerializedName("description")
                           @field:Expose
                           var description: String?,
                           completed: Boolean) {

    @SerializedName("completed")
    @Expose
    var completed: Int? = null

    init {
        this.completed = StatusOfTask.booleanToInteger(completed)
    }
}
