package sheridan.caluagd.todolist_assignment3.front.edit

import sheridan.caluagd.todolist_assignment3.R
import sheridan.caluagd.todolist_assignment3.navigation.NavigationDestination

object ToDoEditDestination : NavigationDestination {
    override val route = "todo_edit"
    override val titleRes = R.string.edit_todo_title
    const val TODO_ID_ARG = "toDoId"
    val routeWithArgs = "$route/{$TODO_ID_ARG}"
}