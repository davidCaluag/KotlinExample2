package sheridan.caluagd.todolist_assignment3.front.Form

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import sheridan.caluagd.todolist_assignment3.domain.Category
import java.util.Date

@Composable
fun ToDoFormBody(
    toDoFormUiState: ToDoFormUiState,
    onTitleChange: (String) -> Unit,
    onMemoChange: (String) -> Unit,
    onPriorityChange: (Float) -> Unit,
    onCategoryChange: (Category) -> Unit,
    onDateChange: (Date) -> Unit,
    onDueChange: (Date) -> Unit,
    onSaveClick: () -> Unit,
    isEdit : Boolean,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    ToDoForm(
        toDoFormModel = toDoFormUiState.toDoFormModel,
        onTitleChange = onTitleChange,
        onMemoChange = onMemoChange,
        onPriorityChange = onPriorityChange,
        onCategoryChange = onCategoryChange,
        onDueChange = onDueChange,
        onDateChange = onDateChange,
        isEdit = isEdit,
        onDeleteClick = onDeleteClick,
        onSaveClick = onSaveClick,
        modifier = Modifier.fillMaxWidth()
    )
    Button(
        onClick = onSaveClick,
        enabled = toDoFormUiState.isEntryValid,
        shape = MaterialTheme.shapes.small,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Save")
    }

        Button(
            onClick = onDeleteClick,
            enabled = isEdit,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Delete")
        }



}