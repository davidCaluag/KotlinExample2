package sheridan.caluagd.todolist_assignment3.front.list


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import sheridan.caluagd.todolist_assignment3.data.repository.ToDoRepository
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val toDoRepository: ToDoRepository
): ViewModel() {

    val toDoListUiState: StateFlow<ToDoUiState> =
        toDoRepository.getAllToDo()
        .map{ list -> ToDoUiState(list.map {toDo -> toDo.toListItemModel()})
        }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT),
                initialValue = ToDoUiState()
        )

    fun toggleProgress(listItemModel: ToDoListItemModel){
        viewModelScope.launch {
            toDoRepository.updateProgress(!listItemModel.isDone, listItemModel.id)
        }
    }

    fun deleteDoneToDo(){
        viewModelScope.launch {
            val list = toDoRepository.finishDoneToDo()
        }
    }

    companion object{
        private const val TIMEOUT = 5_000L
    }
}