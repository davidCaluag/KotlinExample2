//import androidx.compose.material3.DatePicker
//import androidx.compose.material3.DatePickerDialog
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextButton
//import androidx.compose.material3.TimePicker
//import androidx.compose.material3.rememberDatePickerState
//import androidx.compose.material3.rememberTimePickerState
//import androidx.compose.ui.res.stringResource
//import sheridan.caluagd.todolist_assignment3.R
//import sheridan.caluagd.todolist_assignment3.front.Form.TimePickerDialog
//import java.util.Calendar
//
//if (showDueDatePicker) {
//    val day: Calendar = Calendar.getInstance().apply {
//        timeInMillis = toDoFormModel.date.time
//    }
//    val zoneOffset = day.timeZone.getOffset(day.time.time)
//    val datePickerState = rememberDatePickerState(
//        initialSelectedDateMillis = day.timeInMillis + zoneOffset,
//        yearRange = 2024..2027
//    )
//    DatePickerDialog(
//        onDismissRequest = {
//            showDueDatePicker = false
//        },
//        confirmButton = {
//            TextButton(
//                onClick = {
//                    val selected: Calendar = Calendar.getInstance().apply {
//                        timeInMillis = datePickerState.selectedDateMillis!! - zoneOffset
//                    }
//                    val calendar: Calendar = Calendar.getInstance().apply {
//                        timeInMillis = toDoFormModel.date.time
//                        set(
//                            selected.get(Calendar.YEAR),
//                            selected.get(Calendar.MONTH),
//                            selected.get(Calendar.DAY_OF_MONTH)
//                        )
//                    }
//                    onDateChange(calendar.time)
//                    showDueDatePicker = false
//                }
//            ) {
//                Text(text = "Okay")
//            }
//        },
//        dismissButton = {
//            TextButton(
//                onClick = {
//                    showDueDatePicker = false
//                }
//            ) {
//                Text(text = "Cancel")
//            }
//        }
//    ) {
//        DatePicker(
//            state = datePickerState
//        )
//    }
//}
//
//// time picker component
//if (showDueTimePicker) {
//    val calendar: Calendar = Calendar.getInstance().apply { time = toDoFormModel.date }
//    val timePickerState = rememberTimePickerState(
//        initialHour = calendar.get(Calendar.HOUR_OF_DAY),
//        initialMinute = calendar.get(Calendar.MINUTE),
//        is24Hour = false
//    )
//    TimePickerDialog(
//        onDismissRequest = {
//            showDueTimePicker = false
//        },
//        confirmButton = {
//            TextButton(
//                onClick = {
//                    with(calendar){
//                        set(Calendar.HOUR_OF_DAY, timePickerState.hour)
//                        set(Calendar.MINUTE, timePickerState.minute)
//                    }
//                    onDateChange(calendar.time)
//                    showDueTimePicker = false
//                }
//            ) {
//                Text(text = stringResource(R.string.ok))
//            }
//        },
//        dismissButton = {
//            TextButton(
//                onClick = {
//                    showDueTimePicker = false
//                }
//            ) {
//                Text(text = "cancel")
//            }
//        }
//    ) {
//        TimePicker(state = timePickerState)
//    }
//}