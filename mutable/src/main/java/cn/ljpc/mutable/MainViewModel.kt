package cn.ljpc.mutable

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cn.ljpc.mutable.viewmodel.Action
import cn.ljpc.mutable.viewmodel.BaseViewModel

class MainViewModel : BaseViewModel<Action>() {

    val _list: LiveData<List<String>> = MutableLiveData()

    val list = _list

    fun add(input: String) {
        input.apply {

        }
    }


}