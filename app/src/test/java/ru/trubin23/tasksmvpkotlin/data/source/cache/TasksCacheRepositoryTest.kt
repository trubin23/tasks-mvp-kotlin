package ru.trubin23.tasksmvpkotlin.data.source.cache

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.trubin23.tasksmvpkotlin.data.Task

class TasksCacheRepositoryTest {

    private val PROPORTION_COMPLETED_ITEMS = 3
    private val ITEMS_COUNT = 15

    private val tasksCacheRepository: TasksCacheRepository =
            TasksCacheRepository.getInstance()

    @Before
    fun init() {
        removeTasks()

        for (i in 1..ITEMS_COUNT) {
            val task = Task(i.toString(), "=" + i.toString() + "=")
            if (i % PROPORTION_COMPLETED_ITEMS == 0) {
                task.mIsCompleted = true
            }
            tasksCacheRepository.addTask(task)
        }
    }

    private fun removeTasks(){
        val tasks = tasksCacheRepository.getTasks()
        if (tasks != null) {
            for (task in tasks) {
                tasksCacheRepository.removeTask(task.mId)
            }
        }
    }

    @Test
    fun removeTasks_test() {
        removeTasks()

        assertEquals(null, tasksCacheRepository.getTasks()?.size)
    }

    @Test
    fun clearCompletedTask_test() {
        tasksCacheRepository.clearCompletedTasks()

        val expected = ITEMS_COUNT - ITEMS_COUNT / PROPORTION_COMPLETED_ITEMS
        assertEquals(expected, tasksCacheRepository.getTasks()?.size)
    }

    @Test
    fun init_test() {
        assertEquals(ITEMS_COUNT, tasksCacheRepository.getTasks()?.size)
    }
}