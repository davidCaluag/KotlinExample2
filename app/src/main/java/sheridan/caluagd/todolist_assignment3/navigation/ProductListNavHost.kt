package sheridan.caluagd.todolist_assignment3.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import sheridan.caluagd.todolist_assignment3.front.details.ToDoDetailScreen
import sheridan.caluagd.todolist_assignment3.front.details.ToDoDetailViewModel
import sheridan.caluagd.todolist_assignment3.front.edit.EditViewModel
import sheridan.caluagd.todolist_assignment3.front.edit.ToDoEditScreen
import sheridan.caluagd.todolist_assignment3.front.list.ListViewModel
import sheridan.caluagd.todolist_assignment3.front.list.ToDoListScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListNavHost(navHostController: NavHostController, modifier: Modifier = Modifier){
    NavHost(
        navController = navHostController,
        startDestination = ToDoListDestination.route,
        modifier = modifier){

        composable(route = ToDoListDestination.route){
            val viewModel: ListViewModel = hiltViewModel()
            ToDoListScreen(navigateToAdd = { navHostController.navigate("${ToDoEditDestination.route}/${(999)}") }, navigateToDetail = {id -> navHostController.navigate("${ToDoDetailDestination.route}/${id}")} ,navigateToEdit = { id -> navHostController.navigate("${ToDoEditDestination.route}/${id}") }, viewModel = viewModel)
        }

        composable(route = ToDoDetailDestination.routeWithArgs,
            arguments = listOf(navArgument(ToDoDetailDestination.TODO_ID_ARG) {
                type = NavType.IntType
            })){
            val viewModel: ToDoDetailViewModel = hiltViewModel()
            ToDoDetailScreen( modifier = Modifier, viewModel = viewModel, navigateUp = {navHostController.navigate(ToDoListDestination.route)}, edit = { id -> navHostController.navigate("${ToDoEditDestination.route}/${id}") })
        }

        composable(route = ToDoEditDestination.routeWithArgs,
            arguments = listOf(navArgument(ToDoEditDestination.TODO_ID_ARG) {
                type = NavType.IntType
            }))
        {
            val viewModel : EditViewModel = hiltViewModel()

            ToDoEditScreen(
                navigateBack = { navHostController.navigate(ToDoListDestination.route) },
                navigateUp = {navHostController.popBackStack()},
                viewModel = viewModel
                )
        }

    }
}