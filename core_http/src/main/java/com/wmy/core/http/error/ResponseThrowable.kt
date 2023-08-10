package com.wmy.core.http.error

import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLException

class ResponseThrowable : Exception {
    var code: String? = ""
    var errMsg: String? = ""
    var msg: String? = ""
    var data: String? = ""

    constructor(error: ErrorEnum, e: Throwable? = null) : super(e) {
        code = error.getKey().toString()
        errMsg = error.getValue()
        if (e is SSLException) {
            this.code = ErrorEnum.NETWORD_ERROR.getKey().toString()
        }
        if (e is SocketTimeoutException) {
            this.code = ErrorEnum.NETWORD_ERROR.getKey().toString()
        }
        if (e is UnknownHostException) {
            this.code = ErrorEnum.NETWORD_ERROR.getKey().toString()
        }
        if (e is HttpException) {
            if (e.code() != 500 && e.code() != 400 && e.code() != 410) {
                this.code = ErrorEnum.NETWORD_ERROR.getKey().toString()
            }
        }
    }

    constructor(code: String? = "", msg: String?, e: Throwable? = null, data: String? = null, traceId: String? = null) : super(e) {

        this.code = code
        this.data = data
        msg?.let {
            this.msg=msg
            //debug 模式 or 非生产环境增加 后台接口提示


        }
        if (e is SSLException) {
            this.code = ErrorEnum.NETWORD_ERROR.getKey().toString()
        }
        if (e is SocketTimeoutException) {
            this.code = ErrorEnum.NETWORD_ERROR.getKey().toString()
        }
        if (e is UnknownHostException) {
            this.code = ErrorEnum.NETWORD_ERROR.getKey().toString()
        }
        if (e is HttpException) {
            if (e.code() != 500 && e.code() != 400 && e.code() != 410) {
                this.code = ErrorEnum.NETWORD_ERROR.getKey().toString()
            }
        }


    }


}
