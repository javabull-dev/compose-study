package cn.ljpc.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import cn.ljpc.ui.dialog.ShowLoadingDialog

@Composable
fun MainContent(text: String) {
    val isLoading = remember {
        mutableStateOf(false)
    }
    Box {
        Button(onClick = {
            isLoading.apply {
                this.value = true
            }
        }) {
            Text(text = text)
        }
        ShowLoadingDialog(visible = isLoading.value, text = "正在加载中.............")
    }
}
