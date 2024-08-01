package sheridan.caluagd.todolist_assignment3.front.Form

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RatingBar
import android.widget.Spinner
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import sheridan.caluagd.todolist_assignment3.R
import sheridan.caluagd.todolist_assignment3.common.formatDate
import sheridan.caluagd.todolist_assignment3.common.formatTime
import sheridan.caluagd.todolist_assignment3.domain.Category
import java.util.Calendar
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
             enabled : Boolean = true,
             modifier: Modifier = Modifier
) {
    var showCreatedDatePicker by rememberSaveable { mutableStateOf(false) }
    var showCreatedTimePicker by rememberSaveable { mutableStateOf(false) }
    var showDueDatePicker by rememberSaveable { mutableStateOf(false) }
    var showDueTimePicker by rememberSaveable { mutableStateOf(false) }

    if (showDueDatePicker) {
        val day: Calendar = Calendar.getInstance().apply {
            timeInMillis = toDoFormModel.due.time
        }
        val zoneOffset = day.timeZone.getOffset(day.time.time)
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = day.timeInMillis + zoneOffset,
            yearRange = 2024..2027
        )
        DatePickerDialog(
            onDismissRequest = {
                showDueDatePicker = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        val selected: Calendar = Calendar.getInstance().apply {
                            timeInMillis = datePickerState.selectedDateMillis!! - zoneOffset
                        }
                        val calendar: Calendar = Calendar.getInstance().apply {
                            timeInMillis = toDoFormModel.due.time
                            set(
                                selected.get(Calendar.YEAR),
                                selected.get(Calendar.MONTH),
                                selected.get(Calendar.DAY_OF_MONTH)
                            )
                        }
                        onDueChange(calendar.time)
                        showDueDatePicker = false
                    }
                ) {
                    Text(text = "Okay")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDueDatePicker = false
                    }
                ) {
                    Text(text = "Cancel")
                }
            }
        ) {
            DatePicker(
                state = datePickerState
            )
        }
    }

    // time picker component
    if (showDueTimePicker) {
        val calendar: Calendar = Calendar.getInstance().apply { time = toDoFormModel.due }
        val timePickerState = rememberTimePickerState(
            initialHour = calendar.get(Calendar.HOUR_OF_DAY),
            initialMinute = calendar.get(Calendar.MINUTE),
            is24Hour = false
        )
        TimePickerDialog(
            onDismissRequest = {
                showDueTimePicker = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        with(calendar){
                            set(Calendar.HOUR_OF_DAY, timePickerState.hour)
                            set(Calendar.MINUTE, timePickerState.minute)
                        }
                        onDueChange(calendar.time)
                        showDueTimePicker = false
                    }
                ) {
                    Text(text = stringResource(R.string.ok))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDueTimePicker = false
                    }
                ) {
                    Text(text = "cancel")
                }
            }
        ) {
            TimePicker(state = timePickerState)
        }
    }

    // date picker component
    if (showCreatedDatePicker) {
        val day: Calendar = Calendar.getInstance().apply {
            timeInMillis = toDoFormModel.date.time
        }
        val zoneOffset = day.timeZone.getOffset(day.time.time)
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = day.timeInMillis + zoneOffset,
            yearRange = 2024..2027
        )
        DatePickerDialog(
            onDismissRequest = {
                showCreatedDatePicker = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        val selected: Calendar = Calendar.getInstance().apply {
                            timeInMillis = datePickerState.selectedDateMillis!! - zoneOffset
                        }
                        val calendar: Calendar = Calendar.getInstance().apply {
                            timeInMillis = toDoFormModel.date.time
                            set(
                                selected.get(Calendar.YEAR),
                                selected.get(Calendar.MONTH),
                                selected.get(Calendar.DAY_OF_MONTH)
                            )
                        }
                        onDateChange(calendar.time)
                        showCreatedDatePicker = false
                    }
                ) {
                    Text(text = "Okay")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showCreatedDatePicker = false
                    }
                ) {
                    Text(text = "Cancel")
                }
            }
        ) {
            DatePicker(
                state = datePickerState
            )
        }
    }

    // time picker component
    if (showCreatedTimePicker) {
        val calendar: Calendar = Calendar.getInstance().apply { time = toDoFormModel.date }
        val timePickerState = rememberTimePickerState(
            initialHour = calendar.get(Calendar.HOUR_OF_DAY),
            initialMinute = calendar.get(Calendar.MINUTE),
            is24Hour = false
        )
        TimePickerDialog(
            onDismissRequest = {
                showCreatedTimePicker = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        with(calendar){
                            set(Calendar.HOUR_OF_DAY, timePickerState.hour)
                            set(Calendar.MINUTE, timePickerState.minute)
                        }
                        onDateChange(calendar.time)
                        showCreatedTimePicker = false
                    }
                ) {
                    Text(text = stringResource(R.string.ok))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showCreatedTimePicker = false
                    }
                ) {
                    Text(text = "cancel")
                }
            }
        ) {
            TimePicker(state = timePickerState)
        }
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {
        OutlinedTextField(
            value = toDoFormModel.title,
            onValueChange = onTitleChange,
            label = { Text(text = "Title") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = toDoFormModel.memo,
            onValueChange = onMemoChange,
            //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            label = { Text("Memo") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Priority",
                style = MaterialTheme.typography.bodyLarge
            )
            RatingInput(
                rating = toDoFormModel.priority,
                onRatingChange = onPriorityChange
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Category",
                style = MaterialTheme.typography.bodyLarge
            )
            //Category Input
            ConditionInput(
                condition = toDoFormModel.category,
                onCategoryChange = onCategoryChange
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {Text("Date created: ", style = MaterialTheme.typography.titleSmall)
            OutlinedButton(
                onClick = { showCreatedDatePicker = true }
            ) {
                Text(text = formatDate(toDoFormModel.date))
            }
            OutlinedButton(
                onClick = { showCreatedTimePicker = true }
            ) {
                Text(text = formatTime(toDoFormModel.date))
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {Text("Due Date: ", style = MaterialTheme.typography.titleSmall)
            OutlinedButton(
                onClick = { showDueDatePicker = true }
            ) {
                Text(text = formatDate(toDoFormModel.due))
            }
            OutlinedButton(
                onClick = { showDueTimePicker = true }
            ) {
                Text(text = formatTime(toDoFormModel.due))
            }
        }
        if (enabled) {
            Text(
                text = "Required",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_medium))
            )
        }
//        Column(){
//            Text("Information", style = MaterialTheme.typography.titleMedium)
//            Text("Title: ${toDoFormModel.title}" +
//                    "Memo : ${toDoFormModel.memo}" +
//                    "Category: ${toDoFormModel.category}" +
//                    "Saveable: ${toDoFormModel.isValid()}")
//        }

    }
}



@Composable
fun TimePickerDialog(
    title: String = "Select Time",
    onDismissRequest: () -> Unit,
    confirmButton: @Composable (() -> Unit),
    dismissButton: @Composable (() -> Unit)? = null,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
    ) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            tonalElevation = 6.dp,
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min)
                .background(
                    shape = MaterialTheme.shapes.extraLarge,
                    color = containerColor
                ),
            color = containerColor
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    text = title,
                    style = MaterialTheme.typography.labelMedium
                )
                content()
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    dismissButton?.invoke()
                    confirmButton()
                }
            }
        }
    }
}


@Composable
fun RatingInput(
    rating: Float,
    onRatingChange: (Float) -> Unit,
    modifier: Modifier = Modifier
){
    AndroidView(
        factory = { context ->
            RatingBar(context).apply {
                stepSize = 1.0f
                numStars = 3
            }
        },
        update = { ratingBar ->
            ratingBar.rating = rating
            ratingBar.setOnRatingBarChangeListener { _, _, _ ->
                onRatingChange(ratingBar.rating)
            }
        },
        modifier = modifier
    )
}



@Composable
fun ConditionInput(
    condition: Category,
    onCategoryChange: (Category) -> Unit,
    modifier: Modifier = Modifier
){
    val conditions: List<Category> = Category.entries.toList()

    AndroidView(
        //modifier = Modifier.fillMaxWidth(),
        modifier = modifier,
        factory = { context ->
            Spinner(context).apply {
                adapter =
                    ArrayAdapter(
                        context,
                        android.R.layout.simple_spinner_dropdown_item,
                        conditions
                    )
            }
        },
        update = { spinner ->
            spinner.setSelection(condition.ordinal)
            spinner.onItemSelectedListener = ConditionSpinnerAdapter(onCategoryChange)
        }
    )
}

class ConditionSpinnerAdapter(
    val onConditionChange: (Category) -> Unit
) : AdapterView.OnItemSelectedListener {
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        onConditionChange(Category.entries[position])
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        onConditionChange(Category.OTHER)
    }
}

