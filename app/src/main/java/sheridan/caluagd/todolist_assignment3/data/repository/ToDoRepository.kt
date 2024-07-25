package sheridan.caluagd.todolist_assignment3.data.repository

import kotlinx.coroutines.flow.Flow
import sheridan.caluagd.todolist_assignment3.data.local.LocalToDoObject
import sheridan.caluagd.todolist_assignment3.domain.ToDoObject
import java.util.Date

interface ToDoRepository {
    fun getAllToDo() : Flow<List<ToDoObject>>

    suspend fun updateToDo(toDo: ToDoObject)

    suspend fun updateProgress(boolean: Boolean, id: Int)

    suspend fun updateTitle(title: String, id: Int)

    suspend fun updateMemo(memo: String, id: Int)

    suspend fun updatePriority(float: Float, id: Int)

    suspend fun updateTime(date: Date, id: Int)

    suspend fun updateDue(due: Date, id: Int)

    suspend fun deleteProduct(toDo: ToDoObject)

    suspend fun deleteToDoById(id: Int)

    suspend fun finishDoneToDo()

}