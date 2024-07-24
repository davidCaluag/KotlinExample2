package sheridan.caluagd.todolist_assignment3.common


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import sheridan.caluagd.todolist_assignment3.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListTopAppBar(
    canNavigateBack : Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {}
){
    CenterAlignedTopAppBar(
        title = {Text(stringResource(id = R.string.app_name))},
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon ={
            if(canNavigateBack){
                IconButton(onClick = navigateUp){
                    Icon(imageVector = Icons.Filled.Close, contentDescription = "Go Back")
                    }
                }
            }
        )

}