## HTTP 网络框架 retrofit+okhtpp3+coroutines

依赖库
Android kotlin 核心库

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    // define a BOM and its version
   
    // define any required OkHttp artifacts without version
    val okhttpVersion by extra("5.0.0-alpha.11")
    // define a BOM and its version
    implementation(platform("com.squareup.okhttp3:okhttp-bom:${rootProject.extra["okhttpVersion"]}"))
    // define any required OkHttp artifacts without version
    implementation("com.squareup.okhttp3:okhttp:${rootProject.extra["okhttpVersion"]}")
    implementation("com.squareup.okhttp3:logging-interceptor:${rootProject.extra["okhttpVersion"]}")
    //retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    //协程
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.work:work-runtime-ktx:2.8.1")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
