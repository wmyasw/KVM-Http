package com.wmy.mframework

import com.wmy.core.http.BaseHttpViewModel
import com.wmy.core.http.error.ResponseThrowable

class BaseModel: BaseHttpViewModel() {
    override fun showLoadingDialog() {
//        super.showLoadingDialog()
    }

    override fun dismissLoadingDialog() {
//        super.dismissLoadingDialog()
    }

    override fun errorCallBack(err: ResponseThrowable) {
        TODO("Not yet implemented")
    }

}