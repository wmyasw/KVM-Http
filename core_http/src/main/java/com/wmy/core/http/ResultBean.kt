package com.wmy.core.http

import androidx.annotation.Keep

@Keep
interface ResultBean<T> {
    var msg:String?
    var code:String?
    var data:T?
}