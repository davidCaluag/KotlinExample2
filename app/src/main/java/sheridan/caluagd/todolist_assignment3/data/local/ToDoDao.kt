package sheridan.caluagd.todolist_assignment3.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import sheridan.caluagd.todolist_assignment3.data.repository.toLocal
import sheridan.caluagd.todolist_assignment3.domain.ToDoObject
import java.util.Date


@Dao
interface ToDoDao {


    //Get
    @Query("SELECT * FROM ToDo ORDER BY isDone DESC")
    fun getAllToDo(): Flow<List<LocalToDoObject>>

    @Query("SELECT * from ToDo WHERE id = :id")
    fun getProductByIdStream(id: Int): Flow<LocalToDoObject?>
    //Update

    @Update
    suspend fun updateToDo(toDo: LocalToDoObject)

    @Query("SELECT MAX(id) FROM ToDo")
    suspend fun getMaxId(): Int

    //the checklist (isDone) PROBLEM
    @Query("UPDATE ToDo SET isDone =:isDone WHERE id =:id")
    suspend fun updateProgress(id: Int, isDone: Boolean)
    //the title
    @Query("UPDATE ToDo SET title =:title WHERE id =:id")
    suspend fun updateTitle(title: String, id: Int)
    //the memo
    @Query("UPDATE ToDo SET memo =:memo WHERE id =:id")
    suspend fun updateMemo( id: Int, memo: String)
    //priority PROBLEM
    @Query("UPDATE ToDo SET priority =:priority WHERE id =:id")
    suspend fun updatePriority(id: Int, priority: Float)

    @Query("UPDATE ToDo SET due_date =:due_date WHERE id =:id")
    suspend fun updateDue(due_date: Date, id: Int)

    @Query("UPDATE ToDo SET time_stamp =:date WHERE id =:id")
    suspend fun updateTime(date: Date, id: Int)

    //Delete
    @Delete
    suspend fun deleteProduct(toDo: LocalToDoObject)

    @Query("DELETE FROM ToDo WHERE id = :id")
    suspend fun deleteToDoById(id: Int)

    @Query("DELETE FROM ToDo WHERE isDone = true")
    suspend fun finishDoneToDo()


    @Insert
    suspend fun insertToDo(toDo: LocalToDoObject)

    @Transaction
    suspend fun addNewObject(): Int{ //Return the id
        val maxId = getMaxId()
        insertToDo(ToDoObject(id = maxId).toLocal())
        return maxId
    }

}