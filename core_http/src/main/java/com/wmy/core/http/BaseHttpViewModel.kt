package com.wmy.core.http

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wmy.core.http.error.ExceptionHandle
import com.wmy.core.http.error.ResponseThrowable
import kotlinx.coroutines.*

/**
 *@ClassName:
 *@Deseription: 定义基础viewmodel 实现, 处理统一 的ui 事件
 *@author：wangmingyu
 *@date：2020/8/24 10:56
 */
abstract class BaseHttpViewModel : ViewModel(), LifecycleObserver {

    val defUI: UIChange by lazy { UIChange() }
    abstract fun showLoadingDialog()
    abstract fun dismissLoadingDialog()
    abstract fun errorCallBack(err:ResponseThrowable)
    /**
     * UI事件
     */
    inner class UIChange {
//        val showDialog by lazy { SingleLiveEvent<String>() }
//        val dismissDialog by lazy { SingleLiveEvent<Void>() }
//        val toastEvent by lazy { SingleLiveEvent<String>() }
//        val msgEvent by lazy { SingleLiveEvent<Message>() }
//        val netCheck by lazy {  MutableLiveData<String>() }
    }

    /**
     * 网络请求拦截
     * @param block 正常请求返回
     * @param error 错误回调
     * @param complete  请求结束
     * @param isShowDialog Boolean 是否显示loading
     */
    fun launchGo(
        block: suspend CoroutineScope.() -> Unit,
        error: suspend CoroutineScope.(ResponseThrowable) -> Unit = {
            errorCallBack(it)
        },
        complete: suspend CoroutineScope.() -> Unit = {},
        isShowDialog: Boolean = true,

    ) {
        if (isShowDialog)
            showLoadingDialog()
        launchUI {
            handleException(
                withContext(Dispatchers.IO) {
                    block
                },
                {
                    error(it)
                },
                {
                    dismissLoadingDialog()
                    complete()
                })
        }
    }

    /**
     * 所有网络请求都在 viewModelScope 域中启动，当页面销毁时会自动
     * 调用ViewModel的  #onCleared 方法取消所有协程
     */
    private fun launchUI(block: suspend CoroutineScope.() -> Unit) = viewModelScope.launch { block() }

    /**
     * 异常统一处理
     * @param block 正常返回
     * @param error 错误处理
     * @param complete 完成
     */
    private suspend fun handleException(
        block: suspend CoroutineScope.() -> Unit,
        error: suspend CoroutineScope.(ResponseThrowable) -> Unit,
        complete: suspend CoroutineScope.() -> Unit
    ) {
        //同步方法
        coroutineScope {
            try {
                block()
            } catch (e: Exception) {
                error(ExceptionHandle.handleException(e))
            } finally {
                complete()
            }
        }
    }

}