package sheridan.caluagd.todolist_assignment3.front.Form

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import sheridan.caluagd.todolist_assignment3.domain.Category
import java.util.Date

abstract class ToDoFormViewModel : ViewModel() {

    var uiState : ToDoFormUiState by mutableStateOf(ToDoFormUiState())
        protected set

    fun onTitleChange(newTitle: String){
        val newFormModel = uiState.toDoFormModel.copy(title = newTitle)

        uiState = uiState.copy(
            toDoFormModel = newFormModel,
            isEntryValid = newFormModel.isValid()
        )
    }

    fun onMemoChange(newMemo: String){
        val newFormModel = uiState.toDoFormModel.copy(memo = newMemo)
        uiState = uiState.copy(
            toDoFormModel = newFormModel,
            isEntryValid = newFormModel.isValid()
        )
    }

    fun onPriorityChange(newPriority: Float){
        val newFormModel = uiState.toDoFormModel.copy(priority = newPriority)
        uiState = uiState.copy(
            toDoFormModel = newFormModel
        )
    }

    fun onCategoryChange(newCategory: Category){
        val newFormModel = uiState.toDoFormModel.copy(category = newCategory)
        uiState = uiState.copy(
            toDoFormModel = newFormModel
        )
    }

    fun onDateChange(newDate: Date){
        val newFormModel = uiState.toDoFormModel.copy(date = newDate)
        uiState = uiState.copy(
            toDoFormModel = newFormModel
        )
    }

    fun onDueChange(newDate: Date){
        val newFormModel = uiState.toDoFormModel.copy(due = newDate)
        uiState = uiState.copy(
            toDoFormModel = newFormModel
        )
    }
}