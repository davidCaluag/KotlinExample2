package sheridan.caluagd.todolist_assignment3.front.Form

import sheridan.caluagd.todolist_assignment3.domain.Category
import sheridan.caluagd.todolist_assignment3.domain.ToDoObject
import java.util.Date

data class ToDoFormModel(
    val id: Int = 0,
    val title : String = "No Title",
    val memo : String = "No Memo",
    val priority : Float = 0.0F,
    val category : Category = Category.SOMETHING_ELSE,
    val isDone : Boolean = false,
    val date: Date = Date(),
    val due: Date = Date())
{
    fun isValid():Boolean = title.isEmpty() && memo.isEmpty() && (due == date)

    fun toToDo(): ToDoObject = ToDoObject(
        id = (id),
        title = title,
        memo = memo,
        priority = priority,
        category = category,
        isDone = isDone,
        date = date,
        due = due
    )


}

fun ToDoObject.toToDoFormModel(): ToDoFormModel = ToDoFormModel(
    id = id,
    title = title,
    memo = memo,
    priority = priority,
    category = category,
    isDone = isDone,
    date = date,
    due = due

)
