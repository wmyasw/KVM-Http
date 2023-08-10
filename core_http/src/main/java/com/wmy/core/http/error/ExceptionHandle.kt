package com.wmy.core.http.error

import android.net.ParseException
import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.wmy.core.http.ResultBean
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException

/**
 *@Deseription: 错误拦截器
 *@author：wangmingyu
 *@date：2021/8/20 13:38
 */
object ExceptionHandle {

    fun handleException(e: Throwable): ResponseThrowable {
        val ex: ResponseThrowable
        if (e is ResponseThrowable) {
            ex = e
        } else if (e is HttpException) {
            val response = e.response()
            val result =response?.errorBody()?.string()
            val url=response?.raw().toString()
            if (e.code() == 500) return ResponseThrowable("${e.code()}", ErrorEnum.HTTP_ERROR.getValue(), e, url.toString())
            ex = ResponseThrowable("${e.code()}", ErrorEnum.HTTP_ERROR.getValue(), e, url.toString())

        } else if (e is JSONException || e is ParseException) {
            ex = ResponseThrowable(ErrorEnum.PARSE_ERROR, e)
        } else if (e is ConnectException) {
            ex = ResponseThrowable(ErrorEnum.NETWORD_ERROR, e)
        } else if (e is javax.net.ssl.SSLException) {
            ex = ResponseThrowable(ErrorEnum.SSL_ERROR, e)
        } else if (e is NullPointerException) {
            ex = ResponseThrowable(ErrorEnum.NULL_ERROR, e)
        } else if (e is IllegalArgumentException) {
            ex = ResponseThrowable(ErrorEnum.ARGUMENT_ERROR, e)
        } else if (e is IllegalMonitorStateException) {
            ex = ResponseThrowable(ErrorEnum.ARGUMENT_ERROR, e)
        } else if (e is java.net.SocketTimeoutException) {
            ex = ResponseThrowable(ErrorEnum.TIMEOUT_ERROR, e)
        } else if (e is ClassCastException) {
            ex = ResponseThrowable(ErrorEnum.RESULT_ERROR, e)
        } else if (e is java.net.UnknownHostException) {
            ex = ResponseThrowable(ErrorEnum.UNKNOWN, e)
        } else if (e is IllegalStateException) {
            ex = ResponseThrowable(ErrorEnum.RESULT_ERROR, e)
        } else {
            ex = if (!e.message.isNullOrEmpty())
                ResponseThrowable("1000", e.message!!, e)
            else ResponseThrowable(ErrorEnum.UNKNOWN, e)
        }
        return ex
    }
}