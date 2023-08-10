package com.wmy.core.http

import kotlinx.coroutines.Deferred
import retrofit2.http.*


/**
 *@ClassName: ApiService
 *@Deseription: 定义基础请求类型
 *@author：wangmingyu
 *@date：2020/7/13 9:11
 */
interface ApiService {

    @GET
    fun<T> get(@Url url: String, @QueryMap maps: Map<String, Any?>?): Deferred<ResultBean<T?>>
    fun<T> get1(@Url url: String, @QueryMap maps: Map<String, Any?>?): Deferred<T>

    @POST
    fun post(@Url url: String, @QueryMap maps: Map<String, Any>): Deferred<ResultBean<Any?>>

}

