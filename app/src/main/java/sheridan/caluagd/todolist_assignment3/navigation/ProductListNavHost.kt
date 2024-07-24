package sheridan.caluagd.todolist_assignment3.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import sheridan.caluagd.todolist_assignment3.front.list.ListViewModel


@Composable
fun ProductListNavHost(navHostController: NavHostController, modifier: Modifier = Modifier){
    NavHost(
        navController = navHostController,
        startDestination = ToDoListDestination.route,
        modifier = modifier){

        composable(route = ToDoListDestination.route){
            val viewModel: ListViewModel = hiltViewModel()

        }

    }
}