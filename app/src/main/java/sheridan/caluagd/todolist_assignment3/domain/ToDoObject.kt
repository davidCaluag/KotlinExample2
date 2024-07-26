package sheridan.caluagd.todolist_assignment3.domain

import java.util.Date


enum class Category{
    PERSONAL, SCHOOL, WORK, OTHER
}
data class ToDoObject(
    val id: Int = 0,
    val title : String = "Title",
    val memo : String = "Memo",
    val priority : Float = 0F,
    val category : Category = Category.OTHER,
    val isDone : Boolean = false,
    val date: Date = Date(),
    val due: Date = Date()
)
