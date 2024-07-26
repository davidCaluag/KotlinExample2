package sheridan.caluagd.todolist_assignment3.domain

import java.util.Date


enum class Category{
    PERSONAL, SCHOOL, WORK, SOMETHING_ELSE
}
data class ToDoObject(
    val id: Int = 0,
    val title : String = "Title",
    val memo : String = "Memo",
    val priority : Float = 0F,
    val category : Category = Category.SOMETHING_ELSE,
    val isDone : Boolean = false,
    val date: Date = Date(),
    val due: Date = Date()
)
