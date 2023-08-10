package com.wmy.mframework

import com.wmy.core.http.ResultBean

/**
 *@ClassName:com.wmy.mframework
 *@Deseription:
 *@author：wmyasw
 *@date:2023/8/8 18:07
 */
class BaseResultBean<T>(
    override var msg: String?,
    override var code: String?,
    var sequenceScope: String?,
    override var data: T?
) :ResultBean<T>
