package sheridan.caluagd.todolist_assignment3.front.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import sheridan.caluagd.todolist_assignment3.common.formatDate
import sheridan.caluagd.todolist_assignment3.common.formatDateTime
import sheridan.caluagd.todolist_assignment3.front.Form.ToDoFormBody
import sheridan.caluagd.todolist_assignment3.front.list.ToDoUiState
import sheridan.caluagd.todolist_assignment3.navigation.ToDoEditDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoDetailScreen(modifier: Modifier = Modifier, viewModel: ToDoDetailViewModel, navigateUp : () -> Unit, edit: (Int) -> Unit){

    val uiState = viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { stringResource(id = ToDoEditDestination.titleRes) },
                modifier = modifier,
                navigationIcon = {
                    IconButton(onClick = navigateUp) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "back"
                        )
                    }
                }
            )
        }, floatingActionButton = {
            FloatingActionButton(onClick = { edit(uiState.value.detailModel.id) }){
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit To Do")
                //Text("Edit To Do")
            }
        },
        modifier = modifier.padding(8.dp)
    ) { innerPadding ->
        ToDoDetailsBody(
            modifier = Modifier.padding(innerPadding),
            uiState = uiState.value,
            edit = edit
        )
    }

}

@Composable
fun ToDoDetailsBody(modifier: Modifier, uiState: ToDoDetailUiState, edit : (Int) -> Unit){

    Column(){
        Card(modifier = modifier, colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )){
            RowDetail("Title", uiState.detailModel.title)
            RowDetail("memo", uiState.detailModel.memo)
            RowDetail("category", uiState.detailModel.category.name)
            RowDetail("Status", content = uiState.detailModel.isDone.toString())
            RowDetail("Priority", "${uiState.detailModel.priority} / 3")
            RowDetail("Date Created", formatDateTime(uiState.detailModel.date))
            RowDetail("Date Due", formatDateTime    (uiState.detailModel.due))
        }
    }

}

@Composable
fun RowDetail(title: String, content: String, modifier: Modifier = Modifier){
    Row(modifier = modifier
        .padding(8.dp)
        .fillMaxWidth()){
        Text(title)
        Spacer(modifier = modifier.weight(1f))
        Text(content)
    }
}