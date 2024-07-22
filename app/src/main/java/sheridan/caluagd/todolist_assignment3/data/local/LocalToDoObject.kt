package sheridan.caluagd.todolist_assignment3.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import sheridan.caluagd.todolist_assignment3.domain.Category
import java.util.Date


@Entity(tableName = "ToDo")
data class LocalToDoObject (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title : String = "No Title",
    val memo : String = "No Memo",
    val priority : Int = 1,
    val category : Category = Category.SOMETHING_ELSE,
    val isDone : Boolean = false,
    @ColumnInfo(name = "time_stamp") val date: Date = Date()
)