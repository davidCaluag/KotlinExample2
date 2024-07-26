package sheridan.caluagd.todolist_assignment3.front.Form

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import sheridan.caluagd.todolist_assignment3.domain.Category
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoForm(toDoFormModel: ToDoFormModel,
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
){

}