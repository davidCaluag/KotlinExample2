package sheridan.caluagd.todolist_assignment3.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import sheridan.caluagd.todolist_assignment3.data.local.LocalToDoObject
import sheridan.caluagd.todolist_assignment3.data.local.ToDoDao
import sheridan.caluagd.todolist_assignment3.domain.ToDoObject
import java.util.Date

interface ToDoRepository {
    fun getAllToDo() : Flow<List<ToDoObject>>

    fun getProductByIdStream(id: Int): Flow<ToDoObject?>

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

    suspend fun insertToDo(toDo: ToDoObject)

    suspend fun addNewToDo(): Int

}