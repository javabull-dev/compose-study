package cn.ljpc.permission

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.rememberPermissionState

/**
 * 两种获取权限的方式
 */
@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun RequirePermission() {
    val context = LocalContext.current
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val permission = Manifest.permission.READ_EXTERNAL_STORAGE

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) {
            if (it) {
                Toast.makeText(context, "没有获取到权限", Toast.LENGTH_SHORT).show()
            }
        }
    val lifecycleObserver = remember {
        LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                if (!permission.isGrantedPermission(context)) {
                    launcher.launch(permission)
                }
            }
        }
    }

    DisposableEffect(lifecycle, lifecycleObserver) {
        lifecycle.addObserver(lifecycleObserver)
        onDispose {
            lifecycle.removeObserver(lifecycleObserver)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.M)
private fun String.isGrantedPermission(context: Context): Boolean {
    return context.checkSelfPermission(this) == PackageManager.PERMISSION_GRANTED
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequirePermission2() {
    val state =
        rememberPermissionState(permission = Manifest.permission.READ_EXTERNAL_STORAGE)
    PermissionRequired(
        permissionState = state,
        permissionNotGrantedContent = {
            Text(text = "获取权限失败")
        },
        permissionNotAvailableContent = {
            Button(onClick = { state.launchPermissionRequest() }) {
                Text(text = "尚未获取到权限")
            }
        }) {
        Text(text = "获取权限成功")
    }
}