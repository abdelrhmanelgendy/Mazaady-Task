
buildscript{
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Deps.android_tools)
        classpath (Deps.kotlin_plugin)
        classpath (Deps.dagger_hilt_class_pass)

    }
}
tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}
