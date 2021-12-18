package cn.ljpc.lockscreen.viewmodel

sealed class MyAction : Action {
    object NotificationAction : MyAction()
    object OtherAction : MyAction()
}