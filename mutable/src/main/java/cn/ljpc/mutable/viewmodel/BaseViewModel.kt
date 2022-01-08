package cn.ljpc.mutable.viewmodel

import androidx.lifecycle.ViewModel

interface Action

open class BaseViewModel<T : Action>(private val store: MutableDataSource<T> = PublishDataSource()) :
    ViewModel(), MutableDataSource<T> by store {
        //继承了viewmodel
}