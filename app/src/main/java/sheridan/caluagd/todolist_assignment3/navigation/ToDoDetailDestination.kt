package sheridan.caluagd.todolist_assignment3.navigation

import sheridan.caluagd.todolist_assignment3.R

object ToDoDetailDestination : NavigationDestination{
    override val route = "todo_detail"
    override val titleRes = R.string.edit_todo_detail
    const val TODO_ID_ARG = "toDoId"
    val routeWithArgs = "$route/{$TODO_ID_ARG}"
}