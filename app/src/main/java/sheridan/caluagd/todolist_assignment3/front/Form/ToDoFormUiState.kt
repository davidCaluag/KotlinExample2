package sheridan.caluagd.todolist_assignment3.front.Form

import sheridan.caluagd.todolist_assignment3.domain.ToDoObject

data class ToDoFormUiState(
    val toDoFormModel: ToDoFormModel = ToDoFormModel(),
    val isEntryValid: Boolean = false
){

}
fun ToDoFormUiState.save(newFormModel : ToDoFormModel, isEntryValid: Boolean?){

}

fun ToDoObject.toToDoFormUiState(isEntryValid: Boolean = false): ToDoFormUiState {
    return ToDoFormUiState(
        toDoFormModel = this.toToDoFormModel(),
        isEntryValid = isEntryValid
    )
}

