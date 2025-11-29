package com.example.mobileapps2024_2301321082

import com.example.mobileapps2024_2301321082.data.Task
import org.junit.Test
import org.junit.Assert.*

class TaskUnitTest {

    @Test
    fun checkTaskPriorityLogic() {

        val highTask = Task(1, "Важна задача", "Спешно!", 3)


        val lowTask = Task(2, "Лека задача", "Не е спешно", 1)


        assertTrue("High priority should be greater than Low", highTask.priority > lowTask.priority)
    }

    @Test
    fun checkTaskTitleStorage() {
        val title = "Купи хляб"
        val task = Task(0, title, "Черен", 2)

        assertEquals(title, task.title)
    }
}