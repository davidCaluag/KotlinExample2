package sheridan.caluagd.todolist_assignment3.front.list
import android.annotation.SuppressLint
import androidx.annotation.ColorRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat.getColor
import sheridan.caluagd.todolist_assignment3.R
import sheridan.caluagd.todolist_assignment3.common.ListTopAppBar
import java.text.DateFormat
import java.time.Instant
import java.util.Date


object typeOfCard{

    val availableCard : CardColors = CardColors(
        containerColor = Color.Gray,
        contentColor = Color.White,
        disabledContentColor = Color.Red,
        disabledContainerColor = Color.Red)

    val unavailableCard : CardColors = CardColors(
        containerColor = Color.Red,
        contentColor = Color.White,
        disabledContentColor = Color.Red,
        disabledContainerColor = Color.Red

    )
    val finishedCard : CardColors = CardColors(
        containerColor = Color.Cyan,
        contentColor = Color.White,
        disabledContentColor = Color.Red,
        disabledContainerColor = Color.Red

    )

}


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
    val _today: Date = Date.from(Instant.now())

    Scaffold(
        modifier = modifier,//.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            Column(){
                ListTopAppBar(canNavigateBack = false, scrollBehavior = scrollBehavior)
            }


        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToAdd,
                containerColor = colorResource(id = R.color.black),
                contentColor     = colorResource(id = R.color.white),
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(20.dp))
            {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add ToDo")
            }
        },
    ){
        bleh ->
            ListBody(toDoList = listUiState.toDoList,
                onItemClick = navigateToEdit,
                deleteFinishedToDo = viewModel::deleteDoneToDo,
                onToggleSelect = viewModel::toggleProgress,
                modifier = Modifier
                    .padding(bleh),
                today = _today
            )

    }
}

@Composable
fun ListBody(toDoList: List<ToDoListItemModel>,
             deleteFinishedToDo:()-> Unit,
             onItemClick: (Int) -> Unit,
             onToggleSelect: (ToDoListItemModel) -> Unit,
             modifier: Modifier = Modifier,
             today: Date)
{
    Column(){
        Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
            Text("Delete finished tasks: ", style = MaterialTheme.typography.headlineSmall, modifier = Modifier.align(alignment = Alignment.CenterVertically))
            Button(onClick = { deleteFinishedToDo()}, modifier = Modifier) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete")
            }
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(10.dp)){
            if(toDoList.isEmpty()){
                Text(text = "Empty list.", textAlign = TextAlign.Center, style = MaterialTheme.typography.titleLarge)
            }else{
                ToDoList(toDoList = toDoList,
                    onItemClick = onItemClick,
                    onToggleSelect = onToggleSelect,
                    modifier = Modifier.padding(8.dp), today = today)
            }

        }
    }

}

@Composable
private fun ToDoList(toDoList: List<ToDoListItemModel>,
                     onItemClick: (Int) -> Unit,
                     onToggleSelect: (ToDoListItemModel) -> Unit,
                     modifier: Modifier = Modifier, today: Date)
{
    LazyColumn(modifier = Modifier, contentPadding = PaddingValues(vertical = 8.dp, horizontal = 8.dp)){
        items(items = toDoList, key = {it.id}){
            item -> ListItem(
            listItemModel = item,
            onToggleSelect = onToggleSelect,
            today = today,
            modifier = Modifier
                .clickable { onItemClick(item.id) }
                .padding(8.dp))

        }

    }
}


fun isAvailable(due: Date, today: Date, isFinished: Boolean): CardColors{
    
    if(isFinished)
        return typeOfCard.finishedCard
    if(due.after(today))
        return typeOfCard.availableCard

    return typeOfCard.unavailableCard
}

@Composable
private fun ListItem(
    listItemModel: ToDoListItemModel,
    onToggleSelect: (ToDoListItemModel) -> Unit,
    modifier : Modifier,
    today: Date){
    Card(
        colors = isAvailable(listItemModel.due, today, listItemModel.isDone),
        modifier = modifier
            .wrapContentSize()
            .padding(10.dp)){
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)){
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()){
                Checkbox(
                    checked = listItemModel.isDone,
                    onCheckedChange = {onToggleSelect(listItemModel)})
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)){
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth())
                    {
                        Text(text = listItemModel.title,
                            style = MaterialTheme.typography.titleLarge)
                        RatingDisplay(
                            rating = listItemModel.priority.toInt()
                        )
                    }

                    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()){
                        Text(text = listItemModel.memo)
                        Text(text = listItemModel.category.name)
                    }

                    Text("Due Date: ${listItemModel.due}")
                    Text("Date Created: ${listItemModel.date}")
                }
            }
        }

    }
}

@Composable
fun RatingDisplay(rating: Int, modifier: Modifier = Modifier) {
    val displayDescription = pluralStringResource(R.plurals.number_of_stars, count = rating)
    Row(
        // Content description is added here to support accessibility
        Modifier.semantics {
            contentDescription = displayDescription
        }
    ) {
        for(i: Int in 1..3){
            Image(
                modifier = Modifier.size(16.dp),
                painter = painterResource(
                    if(rating >= i) R.drawable.green_star else R.drawable.grey_star
                ),
                contentDescription = null
            )
        }
    }
}