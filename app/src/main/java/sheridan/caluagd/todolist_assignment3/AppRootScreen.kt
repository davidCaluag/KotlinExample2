package sheridan.caluagd.todolist_assignment3

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import sheridan.caluagd.todolist_assignment3.navigation.ProductListNavHost

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppRootScreen(navController: NavHostController = rememberNavController()){
    ProductListNavHost(navHostController = navController)
}