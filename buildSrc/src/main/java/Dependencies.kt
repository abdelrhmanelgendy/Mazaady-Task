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
    val dagger_hilt = "2.38.1"


}

object Deps {
    val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    val android_tools = "com.android.tools.build:gradle:${Versions.android_tools}"
    val kotlin_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_plugin}"
    val android_jUnit_runner = "androidx.test.runner.AndroidJUnitRunner"
    val anroid_app_id = "com.android.application"


    val androidx_core = "androidx.core:core-ktx:${Versions.androidx_core}"
    val androidx_app_compat = "androidx.appcompat:appcompat:${Versions.androidx_app_compat}"
    val android_material = "com.google.android.material:material:${Versions.android_material}"
    val android_x_constraint_layout =
        "androidx.constraintlayout:constraintlayout:${Versions.android_x_constraint_layout}"
    val junit = "junit:junit:${Versions.junit}"
    val android_x_junit = "androidx.test.ext:junit:${Versions.android_x_junit}"
    val dagger_hilt ="com.google.dagger:hilt-android-gradle-plugin:${Versions.dagger_hilt}"


    val kotlin_kapt="kotlin-kapt"
    val dagger_hilt_plugin="dagger.hilt.android.plugin"

}