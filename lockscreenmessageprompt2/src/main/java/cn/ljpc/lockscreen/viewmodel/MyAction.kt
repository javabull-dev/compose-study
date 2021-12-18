package cn.ljpc.lockscreen.viewmodel

sealed class MyAction : Action {
    object IntentAction : MyAction()
    object CloseAction : MyAction()
    object OtherAction : MyAction()
}