package sheridan.caluagd.todolist_assignment3.front.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import sheridan.caluagd.todolist_assignment3.data.repository.ToDoRepository
import sheridan.caluagd.todolist_assignment3.domain.ToDoObject
import sheridan.caluagd.todolist_assignment3.front.Form.ToDoFormModel
import sheridan.caluagd.todolist_assignment3.front.Form.ToDoFormViewModel
import sheridan.caluagd.todolist_assignment3.front.Form.toToDoFormUiState
import sheridan.caluagd.todolist_assignment3.front.list.ToDoUiState
import sheridan.caluagd.todolist_assignment3.navigation.ToDoEditDestination
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val toDoRepository: ToDoRepository
): ToDoFormViewModel() {
    private var toDoId: Int = checkNotNull(savedStateHandle[ToDoEditDestination.TODO_ID_ARG])
    var isEdit : Boolean = false

    init {
        viewModelScope.launch{
        if(toDoId < 0){
            toDoId = toDoRepository.addNewToDo()
        }
            else{
                isEdit = !isEdit
        }
            uiState = toDoRepository.getProductByIdStream(toDoId)
                .filterNotNull()
                .first()
                .toToDoFormUiState(isEntryValid = true)
        }
    }

    fun updateProduct() = viewModelScope.launch{
        val formModel: ToDoFormModel= uiState.toDoFormModel
        if(formModel.isValid())
            toDoRepository.updateToDo(formModel.toToDo())
    }

    //Only available in edit.
    fun deleteProduct() = viewModelScope.launch{

        if(isEdit) //This might be bad-coding.
            toDoRepository.deleteToDoById(toDoId)
    }
}