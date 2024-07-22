package sheridan.caluagd.todolist_assignment3.domain

import java.util.Date


enum class Category{
    PERSONAL, SCHOOL, WORK, SOMETHING_ELSE
}
data class ToDoObject(
    val id: Int = 0,
    val title : String = "No Title",
    val memo : String = "No Memo",
    val priority : Int = 1,
    val category : Category = Category.SOMETHING_ELSE,
    val isDone : Boolean = false,
    val date: Date = Date()
)
