package sheridan.caluagd.todolist_assignment3.front.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import sheridan.caluagd.todolist_assignment3.data.repository.ToDoRepository
import sheridan.caluagd.todolist_assignment3.domain.ToDoObject
import sheridan.caluagd.todolist_assignment3.front.list.ToDoUiState
import sheridan.caluagd.todolist_assignment3.navigation.ToDoDetailDestination
import sheridan.caluagd.todolist_assignment3.navigation.ToDoEditDestination
import javax.inject.Inject


@HiltViewModel
class ToDoDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val toDoRepository: ToDoRepository
) : ViewModel() {

    private var toDoId: Int = checkNotNull(savedStateHandle[ToDoDetailDestination.TODO_ID_ARG])

    val uiState : StateFlow<ToDoDetailUiState> = toDoRepository.getProductByIdStream(toDoId)
        .filterNotNull()
        .map {item -> ToDoDetailUiState(item)}
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = ToDoDetailUiState(ToDoObject())
            )


    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}