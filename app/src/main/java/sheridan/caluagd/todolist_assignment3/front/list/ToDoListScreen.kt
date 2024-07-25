package sheridan.caluagd.todolist_assignment3.front.list
import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import sheridan.caluagd.todolist_assignment3.common.ListTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ToDoListScreen(
    navigateToAdd: () -> Unit,
    navigateToEdit: (Int) -> Unit,
    viewModel: ListViewModel,
    modifier: Modifier = Modifier
){
    val listUiState: ToDoUiState by viewModel.toDoListUiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            ListTopAppBar(canNavigateBack = false, scrollBehavior = scrollBehavior)
        },
        floatingActionButton = {
            FloatingActionButton(onClick = navigateToAdd, shape = MaterialTheme.shapes.medium, modifier = Modifier.padding(
                Dp(20f)
            ))
            {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add ToDo")
            }
        }
    ){
        bleh ->
        ListBody(toDoList = listUiState.toDoList,
                onItemClick = navigateToEdit,
                onToggleSelect = viewModel::toggleProgress,
                modifier = modifier.padding(bleh)
                    .fillMaxSize()
        )

//        Row(){
//            Text("Delete finished To-Do")
//            Button(onClick = { viewModel.deleteDoneToDo() }){
//                Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete")
//            }
//        }
    }
}

@Composable
fun ListBody(toDoList: List<ToDoListItemModel>,
             onItemClick: (Int) -> Unit,
             onToggleSelect: (ToDoListItemModel) -> Unit,
             modifier: Modifier = Modifier)
{

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier){
        if(toDoList.isEmpty()){
            Text(text = "Empty list.", textAlign = TextAlign.Center, style = MaterialTheme.typography.titleLarge)
        }else{
            ToDoList(toDoList = toDoList,
                onItemClick = onItemClick,
                onToggleSelect = onToggleSelect,
                modifier = modifier.padding(8.dp))
        }

    }
}

@Composable
private fun ToDoList(toDoList: List<ToDoListItemModel>,
                     onItemClick: (Int) -> Unit,
                     onToggleSelect: (ToDoListItemModel) -> Unit,
                     modifier: Modifier = Modifier)
{
    LazyColumn(modifier = modifier){
        items(items = toDoList, key = {it.id}){
            item -> ListItem(listItemModel = item, onToggleSelect = onToggleSelect, modifier = modifier.clickable{onItemClick(item.id)})
        }

    }
}

@Composable
private fun ListItem(listItemModel: ToDoListItemModel, onToggleSelect: (ToDoListItemModel) -> Unit, modifier : Modifier){
    Card(modifier = modifier, elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)){
        Column(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)){
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center, modifier = modifier.fillMaxWidth()){
                Checkbox(checked = listItemModel.isDone, onCheckedChange = {onToggleSelect(listItemModel)})
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)){
                    Row(verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth())
                    {
                        Text(text = listItemModel.title,
                            style = MaterialTheme.typography.titleLarge)
                        TODO("Rating Display")
                    }

                    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()){
                        Text(text = listItemModel.memo)
                        Text(text = listItemModel.category.name)
                    }

                    Text("Due Date: ${listItemModel.date}")
                }
            }
        }
    }
}