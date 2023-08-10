package com.wmy.core.http

import android.text.TextUtils
import androidx.lifecycle.ViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.wmy.core.http.error.ExceptionHandle
import com.wmy.core.http.error.ResponseThrowable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Closeable
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext

class EasyHttp {

    private var retrofit: Retrofit? = null
    private val config: EasyHttpConfig? = null
    lateinit var viewModel: BaseHttpViewModel
    var isDebug = true;
    var isEnableErrToast = true
    private val httpLoggingInterceptor: Interceptor by lazy { HttpLoggingInterceptor() }
    private fun getRetrofit(): Retrofit =
        // 获取retrofit的实例
        Retrofit.Builder()
            .baseUrl(UrlConstant.BASE_URL)  //自己配置
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

    /**
     * 初始化OkhttpClient
     */
    private fun getOkHttpClient(): OkHttpClient = OkHttpClient.Builder().apply {
        connectTimeout(60, TimeUnit.SECONDS)// 连接时间：60s超时
        readTimeout(60, TimeUnit.SECONDS)// 读取时间：60s超时
        writeTimeout(60, TimeUnit.SECONDS)// 写入时间：60s超时
        if (isDebug) {
            addInterceptor(httpLoggingInterceptor)
        }// 仅debug模式启用日志过滤器

    }.build()



    fun setViewModel(viewModel: BaseHttpViewModel): EasyHttp {
        this.viewModel = viewModel
        return this
    }


    fun <T> get(
        url: String,
        map: HashMap<String, Any>?,
        sucess: (T) -> Unit,
        error: (String?) -> Unit
    ) {
        if (!::viewModel.isInitialized)
            viewModel.launchGo({
                val result =
                    getRetrofit().create(ApiService::class.java).get<T>(url = url, maps = map)
                        .await()
                if (result.data == null)
                    error.invoke(result.msg)
                sucess.invoke(result.data!!)
            }, {
                if (!isEnableErrToast)
                    error.invoke(it.msg)
            })
        else //当前框架暂时不支持vievmodel 不绑定的情况
            error.invoke("viewModel init error ,check your code . This framework currently does not support writing without using coroutines")

    }

    companion object {
        fun getInstance() = SingleHolder.instance
    }

    object SingleHolder {
        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            EasyHttp()
        }
    }

}