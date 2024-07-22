package sheridan.caluagd.todolist_assignment3.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import sheridan.caluagd.todolist_assignment3.domain.ToDoObject


@Dao
interface ToDoDao {


    //Get
    @Query("SELECT * FROM ToDo ORDER BY isDone DESC")
    fun getAllToDo(): Flow<List<LocalToDoObject>>

    //Update

    @Update
    suspend fun updateToDo(toDo: LocalToDoObject)

    //the checklist (isDone)
    @Query("UPDATE ToDo SET isDone = :_string WHERE id = :id")
    suspend fun updateProgress(_string: Boolean, id: Int)
    //the title
    @Query("UPDATE ToDo SET title = :title WHERE id = :id")
    suspend fun updateTitle(title: String, id: Int)
    //the memo
    @Query("UPDATE ToDo SET memo = :memo WHERE id = :id")
    suspend fun updateMemo(memo: String, id: Int)
    //priority
    @Query("UPDATE ToDo SET priority = :_string WHERE id = :id")
    suspend fun updatePriority(_string: Int, id: Int)



    //Delete
    @Delete
    suspend fun deleteProduct(toDo: ToDoObject)

    @Query("DELETE FROM ToDo WHERE id = :id")
    suspend fun deleteToDoById(id: Int)

}