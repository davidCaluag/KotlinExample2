package sheridan.caluagd.todolist_assignment3.data.repository

import kotlinx.coroutines.flow.Flow
import sheridan.caluagd.todolist_assignment3.domain.ToDoObject

interface ToDoRepository {
    fun getAllToDo() : Flow<List<ToDoObject>>

    suspend fun updateToDo(toDo: ToDoObject)

    suspend fun updateProgress(_string: Boolean, id: Int)

    suspend fun updateTitle(title: String, id: Int)

    suspend fun updateMemo(memo: String, id: Int)

    suspend fun updatePriority(_string: Int, id: Int)

    suspend fun deleteProduct(toDo: ToDoObject)

    suspend fun deleteToDoById(id: Int)

}