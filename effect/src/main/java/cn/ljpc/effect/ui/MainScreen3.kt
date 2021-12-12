package cn.ljpc.effect.ui

import android.util.Log
import android.util.TypedValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import cn.ljpc.effect.R

@Composable
fun MainContent3() {
    val context = LocalContext.current
    val resources = context.resources
    val string = "bBtTon tXt"
    //通过name查找id
    val stringName = string.lowercase().replace(' ', '_')
    val id = resources.getIdentifier(stringName, "string", context.packageName)
    Log.d("", "id是否相等 ${id} --- ${R.string.button_txt}  ${id == R.string.button_txt}")
//    val typedValue = TypedValue()
//    resources.getValue("validate_text", typedValue, true)
//    Log.d("", "typedValue=${typedValue.resourceId}")
}