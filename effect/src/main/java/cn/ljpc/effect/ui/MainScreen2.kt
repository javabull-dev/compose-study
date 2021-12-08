package cn.ljpc.effect.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import cn.ljpc.effect.R

@Composable
fun MainContent2() {
    val context = LocalContext.current
    //当前使用的locale
    val currentLocale = Locale.current
    val currentLocale2 = java.util.Locale.getDefault()
    Log.d("", "当前的locale：${currentLocale}--${currentLocale2}")

    //获取当前android系统所支持的locale
    val availableLocales = java.util.Locale.getAvailableLocales()
    availableLocales.forEach {
//        Log.d("", "当前的locale：${it}")
    }

    val locales = context.resources.configuration.locales
    Log.i("", "context.resources.configuration.locales=${locales}")

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = { }) {
            Text(stringResource(id = R.string.button_txt))
        }
    }
}