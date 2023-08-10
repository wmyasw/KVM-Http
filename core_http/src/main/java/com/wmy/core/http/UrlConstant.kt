package com.wmy.core.http

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class UrlConstant {
    companion object {
        val BASE_URL: String="www.baidu.com"
    }
    fun defaultBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .followSslRedirects(true)
            .connectTimeout(180L, TimeUnit.SECONDS)
            .readTimeout(180L, TimeUnit.SECONDS)
            .writeTimeout(180L, TimeUnit.SECONDS)

    }

}
