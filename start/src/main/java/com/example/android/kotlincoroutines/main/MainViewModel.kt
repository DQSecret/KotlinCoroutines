package com.example.android.kotlincoroutines.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

/**
 * MainViewModel designed to store and manage UI-related data in a lifecycle conscious way. This
 * allows data to survive configuration changes such as screen rotations. In addition, background
 * work such as fetching network results can continue through configuration changes and deliver
 * results after the new Fragment or Activity is available.
 */
class MainViewModel : ViewModel() {

    /**
     * Request a snackbar to display a string.
     *
     * This variable is private because we don't want to expose MutableLiveData
     *
     * MutableLiveData allows anyone to set a value, and MainViewModel is the only
     * class that should be setting values.
     */
    private val _snackBar = MutableLiveData<String>()

    /**
     * Request a snackbar to display a string.
     */
    val snackbar: LiveData<String>
        get() = _snackBar

    /**
     * Wait one second then display a snackbar.
     */
    fun onMainViewClicked() {
//        // TODO: Replace with coroutine implementation
//        BACKGROUND.submit {
//            Thread.sleep(1_000)
//            // use postValue since we're in a background thread
//            _snackBar.postValue("Hello, from threads!")
//        }
        // 以下为协程代码
        viewModelScope.launch {
            delay(1_000)
            _snackBar.value = "Hello, from coroutines!"
        }
    }

    /**
     * Called immediately after the UI shows the snackbar.
     */
    fun onSnackbarShown() {
        _snackBar.value = null
    }

    ////////////////////////////////////////////////////////////
    /// 以上是 callback 的代码, 以下使用协程来实现
    ////////////////////////////////////////////////////////////

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    ////////////////////////////////////////////////////////////
    /// 利用第三方库, 省略以上样板代码
    ////////////////////////////////////////////////////////////

    /**
     * No need to override onCleared()
     */
    private fun maleNetworkRequest() {
        viewModelScope.launch(Dispatchers.IO) {
        }
    }
}