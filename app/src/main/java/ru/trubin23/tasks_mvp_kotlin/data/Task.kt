package ru.trubin23.tasks_mvp_kotlin.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "tasks")
data class Task @JvmOverloads constructor(
        var mTitle: String = "",
        var mDescription: String = "",
        @PrimaryKey @ColumnInfo(name = "entryId") var mId: String = UUID.randomUUID().toString()
) {

    var isCompleted = false

    val titleForList: String
        get() = if (mTitle.isNotEmpty()) {
            mTitle
        } else {
            mDescription
        }

}