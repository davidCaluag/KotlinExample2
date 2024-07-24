package sheridan.caluagd.todolist_assignment3.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import sheridan.caluagd.todolist_assignment3.domain.Category
import java.util.Date


@Entity(tableName = "ToDo")
data class LocalToDoObject (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title : String,
    val memo : String,
    val priority : Float,
    val category : Category,
    val isDone : Boolean,
    @ColumnInfo(name = "time_stamp") val date: Date,
    @ColumnInfo(name = "due_date") val due: Date
)