package sheridan.caluagd.todolist_assignment3.front.details

import sheridan.caluagd.todolist_assignment3.domain.Category
import sheridan.caluagd.todolist_assignment3.domain.ToDoObject
import java.util.Date
import kotlin.math.roundToInt

data class ToDoDetailUiState(val detailModel: ToDoDetailsModel)
{
    constructor(toDo: ToDoObject) : this(
        detailModel = toDo.toDetailsModel()
    )

}


data class ToDoDetailsModel(
    val id: Int = 0,
    val title : String = "Title",
    val memo : String = "Memo",
    val priority : Float = 0F,
    val category : Category = Category.OTHER,
    val isDone : Boolean = false,
    val date: Date = Date(),
    val due: Date = Date()
){

    constructor (toDo: ToDoObject) : this(
        id = toDo.id,
        title = toDo.title,
        memo = toDo.memo,
        priority = toDo.priority,
        category = toDo.category,
        isDone = toDo.isDone,
        date = toDo.date,
        due = toDo.due
    )
}

fun ToDoObject.toDetailsModel() = ToDoDetailsModel(this)