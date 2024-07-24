package sheridan.caluagd.todolist_assignment3.front.list

import sheridan.caluagd.todolist_assignment3.domain.Category
import sheridan.caluagd.todolist_assignment3.domain.ToDoObject
import java.util.Date

data class ToDoListItemModel(
    val id: Int,
    val title : String,
    val memo : String,
    val priority : Float,
    val category : Category,
    val isDone : Boolean,
    val date: Date,
    val due: Date
){
    constructor(toDoObject: ToDoObject):this(
        id = toDoObject.id,
        title = toDoObject.title,
        memo = toDoObject.memo,
        priority = toDoObject.priority,
        category = toDoObject.category,
        isDone = toDoObject.isDone,
        date = toDoObject.date,
        due = toDoObject.due
    )
}

fun ToDoObject.toListItemModel() = ToDoListItemModel(this)