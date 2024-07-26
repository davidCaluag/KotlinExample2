package sheridan.caluagd.todolist_assignment3.front.edit

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import sheridan.caluagd.todolist_assignment3.front.Form.ToDoFormBody

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoEditScreen(navigateBack: () -> Unit,
                   navigateUp : () -> Unit,
                   viewModel: EditViewModel,
                   modifier: Modifier = Modifier,
                   scrollBehavior: TopAppBarScrollBehavior? = null
){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { "To Do Edit" },
                modifier = modifier,
                scrollBehavior = scrollBehavior,
                navigationIcon = {
                    IconButton(onClick = navigateUp) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "back"
                        )
                    }
                }
            )
        },
        modifier = modifier
    ) { innerPadding ->
        ToDoFormBody(
            toDoFormUiState = viewModel.uiState,
            onTitleChange = viewModel::onTitleChange,
            onMemoChange = viewModel::onMemoChange,
            onCategoryChange = viewModel::onCategoryChange,
            onPriorityChange = viewModel::onPriorityChange,
            onDueChange = viewModel::onDueChange,
            onDateChange = viewModel::onDateChange,
            onSaveClick = {
                viewModel.updateProduct()
                navigateBack()
            },
            modifier = Modifier.padding(innerPadding)
        )
    }


}