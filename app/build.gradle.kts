import org.jetbrains.kotlin.gradle.plugin.*

plugins {
    id(Deps.anroid_app_id)
    kotlin("android")
    id(Deps.kotlin_kapt)
    id ("com.google.dagger.hilt.android")

}

subprojects {
    apply(plugin = Deps.dagger_hilt_plugin)
}

android {
    namespace = Versions.app_namespace
    compileSdk = 32

    defaultConfig {
        applicationId = Versions.app_namespace
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionCode = Versions.versionCode
        versionName = Versions.versionName

        testInstrumentationRunner = Deps.android_jUnit_runner
    }

    buildTypes {
        getByName("release") {

            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = Versions.jvmTarget
    }
    buildFeatures {
        viewBinding  = true
    }
}

dependencies {


    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    //test
    androidTestImplementation(Deps.androidx_junit)
    androidTestImplementation(Deps.androidx_espresso)
    testImplementation(Deps.powermock_api_mockito)
    testImplementation(Deps.powermock_module)
    testImplementation(Deps.powermock_module_junit4_rule_agent)
    testImplementation(Deps.powermock_module_junit4_rule)
    testImplementation(Deps.junit_test)
    testImplementation(Deps.junit)
    androidTestImplementation(Deps.android_x_junit)
    androidTestImplementation(Deps.espresso)


    //android
    implementation(Deps.androidx_core)
    implementation(Deps.androidx_app_compat)
    implementation(Deps.android_material)
    implementation(Deps.android_x_constraint_layout)

//dagger
    implementation(Deps.dagger_hilt_dep)
    kapt(Deps.dagger_hilt_annotation)

    //glide
    implementation(Deps.glide)
    implementation(Deps.glide_compiler)

    //viewModel
    implementation(Deps.coroutines_viewModel_life_cycle)
    implementation(Deps.coroutines_runtime_life_cycle)

    //retrofit
    implementation(Deps.retrofit)
    implementation(Deps.gson_converter)

    implementation(Deps.circular_image)
    implementation(Deps.ok_http_interceptor)
    implementation(Deps.sdp)
    implementation(Deps.fragment_version)
    implementation(Deps.life_cycle_run_time)
    
    implementation(Deps.youtube_player)


}