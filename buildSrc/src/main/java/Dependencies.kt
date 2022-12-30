object Versions {
    val espresso = "3.5.0"
    val android_tools = "7.3.0"
    val kotlin_plugin = "1.7.0"

    val app_namespace = "org.mazaady.com"

    val minSdk = 21
    val targetSdk = 32
    val versionCode = 1
    val versionName = "1.0"
    val androidx_core = "1.7.0"
    val androidx_app_compat = "1.5.1"


    val android_material = "1.7.0"
    val android_x_constraint_layout = "2.1.4"
    val junit = "4.13.2"
    val android_x_junit = "1.1.4"
    val dagger_hilt = "2.42"
    val jvmTarget = "1.8"

    val dagger_hilt_dep = "2.42"


    val glide = "4.12.0"
    val glide_compiler = "4.12.0"
    val coroutines = "1.5.2"
    val coroutines_life_cycle = "2.4.1"
    val coroutines_runtime_life_cycle = "2.4.1"
    val retrofit = "2.9.0"


    val life_cycle_ext = "1.1.1"
    val circular_image = "3.1.0"
    val okHttp_interceptor = "4.9.1"
    val androidx_junit = "1.1.3"
    val androidx_espresso = "3.4.0"
    val powermock_module = "1.6.2"
    val sdp = "1.0.6"
    val fragment_version = "1.5.0-alpha05"
    val lifecycle_version = "2.5.1"

}

object Deps {
    val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"


    val junit ="junit:junit:"
    val androidx_junit ="androidx.test.ext:junit:${Versions.androidx_junit}"
    val androidx_espresso ="androidx.test.espresso:espresso-core:${Versions.androidx_espresso}"
    val powermock_api_mockito ="org.powermock:powermock-api-mockito:${Versions.powermock_module}"
    val powermock_module_junit4_rule_agent ="org.powermock:powermock-module-junit4-rule-agent:${Versions.powermock_module}"
    val powermock_module_junit4_rule ="org.powermock:powermock-module-junit4-rule:${Versions.powermock_module}"
    val powermock_module ="org.powermock:powermock-module-junit4:${Versions.powermock_module}"

    val android_tools = "com.android.tools.build:gradle:${Versions.android_tools}"
    val kotlin_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_plugin}"
    val android_jUnit_runner = "androidx.test.runner.AndroidJUnitRunner"
    val anroid_app_id = "com.android.application"


    val androidx_core = "androidx.core:core-ktx:${Versions.androidx_core}"
    val androidx_app_compat = "androidx.appcompat:appcompat:${Versions.androidx_app_compat}"
    val android_material = "com.google.android.material:material:${Versions.android_material}"
    val android_x_constraint_layout =
        "androidx.constraintlayout:constraintlayout:${Versions.android_x_constraint_layout}"
    val junit_test = "junit:junit:${Versions.junit}"
    val android_x_junit = "androidx.test.ext:junit:${Versions.android_x_junit}"
    val dagger_hilt_class_pass =
        "com.google.dagger:hilt-android-gradle-plugin:${Versions.dagger_hilt}"


    val kotlin_kapt = "kotlin-kapt"
    val dagger_hilt_plugin = "dagger.hilt.android.plugin"

    val dagger_hilt_dep = "com.google.dagger:hilt-android:${Versions.dagger_hilt_dep}"
    val dagger_hilt_annotation =
        "com.google.dagger:hilt-android-compiler:${Versions.dagger_hilt_dep}"


    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glide_compiler}"


    val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    val coroutines_viewModel_life_cycle =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.coroutines_life_cycle}"
    val coroutines_runtime_life_cycle =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.coroutines_runtime_life_cycle}"

    val retrofit= "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val gson_converter= "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"


//    val lifecycle_ext= "android.arch.lifecycle:extensions:${Versions.life_cycle_ext}"
//    val lifecycle_compiler= "android.arch.lifecycle:compiler:${Versions.life_cycle_ext}"
  val sdp= "com.intuit.sdp:sdp-android:${Versions.sdp}"

    val circular_image= "de.hdodenhof:circleimageview:${Versions.circular_image}"
    val  ok_http_interceptor= "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp_interceptor}"
    val fragment_version= "androidx.fragment:fragment-ktx:${Versions.fragment_version}"
    val life_cycle_run_time "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version"

}