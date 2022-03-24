package com.ibrahimengin.was.view

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.WASTheme
import com.ibrahimengin.was.ui.theme.Shapes
import java.util.*

@Composable
fun DatePickField(){
    val year: Int
    val month: Int
    val day: Int
    val context = LocalContext.current

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val date = remember { mutableStateOf("") }
    val datePickerDialog = DatePickerDialog(context,{
            _:DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            date.value = "$dayOfMonth/$month/$year"
        },year,month,day)

    OutlinedTextField(value = date.value, onValueChange = {date.value = it},
        modifier = Modifier.fillMaxWidth().clickable {datePickerDialog.show()},
        enabled = false,
        singleLine = true,
        shape = Shapes.medium,
        label = { Text("Birthday") }
    )
}


@Preview
@Composable
fun PreviewDatePicker(){
    WASTheme { DatePickField() }
}

