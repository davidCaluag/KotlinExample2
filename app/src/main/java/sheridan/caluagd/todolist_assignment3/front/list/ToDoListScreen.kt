package sheridan.caluagd.todolist_assignment3.front.list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import sheridan.caluagd.todolist_assignment3.common.ListTopAppBar
import androidx.compose.foundation.lazy.items


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ToDoListScreen(
    navigateToEdit: () -> Unit,
    viewModel: ListViewModel,
    modifier: Modifier = Modifier
){
    val listUiState: ToDoUiState by viewModel.toDoListUiState.collectAsState()
    val _scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(_scrollBehavior.nestedScrollConnection),
        topBar = {
            ListTopAppBar(canNavigateBack = false, scrollBehavior = _scrollBehavior)
        },
        floatingActionButton = {
            FloatingActionButton(onClick = navigateToEdit, shape = MaterialTheme.shapes.medium, modifier = Modifier.padding(Dp(20f)))
            {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add ToDo")
            }
        }
    ){ bleh ->
        ListBody(toDoList = listUiState.toDoList,
                onItemClick = TODO(),
                onToggleSelect = TODO())
    }
}

@Composable
fun ListBody(toDoList: List<ToDoListItemModel>,
             onItemClick: (Int) -> Unit,
             onToggleSelect: (ListViewModel) -> Unit,
             modifier: Modifier = Modifier)
{
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier){
        if(toDoList.isEmpty()){
            Text("Empty list.", textAlign = TextAlign.Center, style = MaterialTheme.typography.titleLarge)
        }else{
            ToDoList(toDoList = toDoList,
                onItemClick = onItemClick,
                onToggleSelect = onToggleSelect)
        }

    }
}

@Composable
private fun ToDoList(toDoList: List<ToDoListItemModel>,
                     onItemClick: (Int) -> Unit,
                     onToggleSelect: (ListViewModel) -> Unit,
                     modifier: Modifier = Modifier)
{
    LazyColumn(modifier = modifier){
        items(items = toDoList, key = {it.id}){
            item -> ListItem(listItemModel = item)
        }

    }
}

@Composable
private fun ListItem(listItemModel: ToDoListItemModel, onItemClick: (Int) -> Unit, onToggleSelect: (ListViewModel) -> Unit){

}