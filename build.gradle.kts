
buildscript{
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Deps.android_tools)
        classpath (Deps.kotlin_plugin)
        classpath (Deps.dagger_hilt_class_pass)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")

    }
}
tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}
