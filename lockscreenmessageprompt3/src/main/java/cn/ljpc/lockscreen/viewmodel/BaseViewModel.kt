package cn.ljpc.lockscreen.viewmodel

import androidx.lifecycle.ViewModel

interface Action

open class BaseViewModel<T : Action>(private val store: MutableDataSource<T> = PublishDataSource()) :
    ViewModel(), MutableDataSource<T> by store {
}