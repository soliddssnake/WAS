package com.ibrahimengin.was.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.WASTheme
import com.ibrahimengin.was.ui.theme.Shapes

@Composable
fun CustomOutlinedTextField(
    customStringValue: String,
    customTextFieldFunc: (String) -> Unit,
    labelText: String,
    errorInput: Boolean? = false,
    keyboardOption: KeyboardOptions? = KeyboardOptions.Default
) {
    OutlinedTextField(value = customStringValue,
        onValueChange = customTextFieldFunc,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = keyboardOption!!,
        isError = errorInput!!,
        singleLine = true,
        shape = Shapes.medium,
        label = { Text(labelText) })
}

@Composable
fun PasswordField(
    passwordValue: String, passwordFieldFunc: (String) -> Unit, labelText: String, errorInput: Boolean? = false,
) {
    val passwordVisible = remember { mutableStateOf(false) }
    OutlinedTextField(
        value = passwordValue,
        onValueChange = passwordFieldFunc,
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        isError = errorInput!!,
        singleLine = true,
        shape = Shapes.medium,
        label = { Text(labelText) },
        trailingIcon = {
            val image = if (passwordVisible.value) Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff

            val description = if (passwordVisible.value) "Hide password" else "Show password"

            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(imageVector = image, description)
            }
        }

    )
}

@Preview
@Composable
fun PreviewTextField() {
    WASTheme {
        val loginUserName = remember { mutableStateOf("") }
        //CustomOutlinedTextField(loginUserName.value,{if (it.length <= 50) loginUserName.value = it },"LABEL")
        PasswordField(loginUserName.value, { loginUserName.value = it }, "password")
    }
}