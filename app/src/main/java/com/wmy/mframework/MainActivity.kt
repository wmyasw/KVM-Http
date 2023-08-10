package com.wmy.mframework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wmy.core.http.EasyHttp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    suspend fun ok(){
//        EasyHttp.getInstance().setViewModel()
        EasyHttp.getInstance().get<BaseResultBean<Test>>("", hashMapOf(),{

        },{

        })

    }
}