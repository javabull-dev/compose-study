package cn.ljpc.effect.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import cn.ljpc.effect.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import kotlinx.coroutines.launch

@Composable
fun MainContent() {
    val context = LocalContext.current
    val state = remember {
        mutableStateOf(false)
    }

    val count = remember {
        mutableStateOf(0)
    }

    val coroutineScope = rememberCoroutineScope()

    val count2 = remember {
        mutableStateOf(0)
    }
    val dialogState = remember {
        mutableStateOf(false)
    }
    val materialDialogState = remember {
        mutableStateOf(false)
    }
    val themeState = remember {
        mutableStateOf(false)
    }
    SideEffect {
        Log.d("", "SideEffect终于被调用了")
    }
    LaunchedEffect(key1 = state.value) {
        Log.i("", "LaunchedEffect块被调用，state值发生改变...state=$state")
    }

//    在组合完成时执行
//    SideEffect {
    //可执行与组合无关的逻辑
//    }

//    produceState(initialValue = , producer = ) //将非 Compose 状态转换为 Compose 状态，返回State

    //在不借助state的情况下刷新UI
//    currentRecomposeScope.invalidate()
    /**
     * 当直接子composition发生改变时，父composition页会重组
     */
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "当前的状态：${count.value}")
        Text(text = "count2的值：${count2.value}")
        Row(modifier = Modifier.padding(top = 20.dp)) {
            Button(onClick = { count.value++ }) {
                Text(text = "增加count的值")
            }
            Button(onClick = { state.value = !state.value }) {
                Text(text = "改变state的值")
            }
            Button(onClick = {
                count2.value++
                coroutineScope.launch {
                    Log.i("", "当前count2=${count2.value}")
                }
            }) {
                Text(text = "增加count2的值${count2.value}")
            }
            Button(onClick = { dialogState.value = true }) {

            }
        }
        Row(modifier = Modifier.padding(20.dp)) {
            Button(onClick = { materialDialogState.value = true }) {
                Text(text = "弹出MaterialDialog")
            }
            Button(onClick = { themeState.value = !themeState.value }) {
                Text(text = "切换material dialog主题")
            }
        }
    }
    if (dialogState.value) {
        AlertDialog(onDismissRequest = { dialogState.value = false },
            buttons = {
                Button(onClick = {}) {
                    Text(text = "确认")
                }
            })
    }

    //MaterialAlertDialogBuilder 的使用
    if (materialDialogState.value) {
        val dialog = MaterialAlertDialogBuilder(
            context,
            if (themeState.value) R.style.Theme_Effect_Light else R.style.Theme_Effect_Dark
        )
            .setTitle("提示")
            .setMessage("是否删除?")
            .setPositiveButton("删除") { _, _ ->
                Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show()
                materialDialogState.value = false
            }
            .setNegativeButton("取消") { _, _ ->
                Toast.makeText(context, "删除取消", Toast.LENGTH_SHORT).show()
                materialDialogState.value = false
            }
            .setCancelable(false)
        //设置material dialog为圆角
        val alertBackground = dialog.background as MaterialShapeDrawable
        alertBackground.shapeAppearanceModel = alertBackground.shapeAppearanceModel
            .toBuilder()
            .setAllCorners(
                CornerFamily.ROUNDED,
                16.dp.value
            ) //context.resources.getDimension(R.dimen.dp16)
            .build()
        dialog.show()
    }
}