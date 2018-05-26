package ru.trubin23.tasks_mvp_kotlin.data.source.local

import android.arch.persistence.room.*
import ru.trubin23.tasks_mvp_kotlin.data.Task

@Dao
interface TasksDao {

    @Query("SELECT * FROM tasks")
    fun getTasks(): List<Task>

    @Query("SELECT * FROM tasks WHERE entryId = :taskId")
    fun getTaskById(taskId: String): Task?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: Task)

    @Update
    fun updateTask(task: Task): Int

    @Query("UPDATE tasks SET completed = :completed WHERE entryId = :taskId")
    fun updateCompleted(taskId: String, completed: Boolean)
}