package com.ibrahimengin.was.view

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cake
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.WASTheme
import com.ibrahimengin.was.ui.theme.Shapes
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun DatePickField(
    labelText: String,
    trailingIconInput: ImageVector? = null,
    contentDescriptionIcon: String? = null,
    date: MutableState<String>
) {
    val year: Int
    val month: Int
    val day: Int
    val context = LocalContext.current

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val datePickerDialog = DatePickerDialog(context, { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        val myFormat = "dd/MM/yy"
        val simpleDateFormat = SimpleDateFormat(myFormat, Locale.US)
        date.value = simpleDateFormat.format(calendar.time)
    }, year, month, day)

    OutlinedTextField(value = date.value,
        onValueChange = { date.value = it },
        modifier = Modifier.fillMaxWidth().clickable { datePickerDialog.show() },
        enabled = false,
        singleLine = true,
        shape = Shapes.medium,
        label = { Text(labelText) },
        trailingIcon = {
            Icon(trailingIconInput!!, contentDescriptionIcon)
        })
}


@Preview
@Composable
fun PreviewDatePicker() {
    WASTheme { DatePickField("Birthday", Icons.Filled.Cake, "Birthday", mutableStateOf("12/12/1999")) }
}

