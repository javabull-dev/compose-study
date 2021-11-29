package cn.ljpc.ui.input

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.ljpc.ui.theme.ComposestudyTheme

@Composable
fun InputDemo() {
    val inputData = remember {
        mutableStateOf("")
    }
    val text by remember {
        mutableStateOf(TextFieldValue(""))
    }
    TextField(
        value = inputData.value,
        onValueChange = { change ->
            inputData.value = change
        },
        label = {
            Text(text = "name")
        }
    )
    Spacer(modifier = Modifier.height(8.dp))
    OutlinedTextField(
        value = inputData.value,
        onValueChange = {
            inputData.value = it
        },
        label = {
            Text(text = "name")
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = null
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.Refresh,
                contentDescription = null
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
@Preview(showBackground = true)
fun PreviewInputDemo() {
    ComposestudyTheme {
        Column {
            InputDemo()
        }
    }
}