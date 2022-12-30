import org.jetbrains.kotlin.gradle.plugin.*
plugins {
    id (Deps.anroid_app_id)
    kotlin ("android")
    id (Deps.kotlin_kapt)
}

subprojects {
    apply(plugin = "dagger.hilt.android.plugin")
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
                proguardFiles (getDefaultProguardFile ("proguard-android-optimize.txt"), "proguard-rules.pro")

        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility  = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(Deps.androidx_core)
    implementation(Deps.androidx_app_compat)
    implementation(Deps.android_material)
    implementation(Deps.android_x_constraint_layout)
    testImplementation(Deps.junit)
    androidTestImplementation(Deps.android_x_junit)
    androidTestImplementation(Deps.espresso)
    implementation("com.google.dagger:hilt-android:2.38.1")
    kapt("com.google.dagger:hilt-android-compiler:2.38.1")
}