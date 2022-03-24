package com.ibrahimengin.was.view

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cake
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.WASTheme
import com.ibrahimengin.was.ui.theme.Shapes
import java.util.*

@Composable
fun DatePickField(){
    val year: Int
    val month: Int
    val day: Int
    val context = LocalContext.current
    val colorVal = MaterialTheme.colors.onSurface.copy(alpha = TextFieldDefaults.IconOpacity)

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

    Button(onClick = {datePickerDialog.show()}, colors = ButtonDefaults.buttonColors(Color.Transparent),
        modifier = Modifier.fillMaxWidth().padding(top = 6.dp).height(56.dp)
            .border(1.dp, color = colorVal, shape = Shapes.medium),
        shape = Shapes.medium){
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Text(date.value.ifEmpty { "Birthday" }, color = colorVal, fontSize = 15.sp)
            Icon(imageVector = Icons.Filled.Cake, contentDescription = null, tint = colorVal) }
    }
}

@Preview
@Composable
fun PreviewDatePicker(){
    WASTheme { DatePickField() }
}

