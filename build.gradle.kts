
buildscript{
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Deps.android_tools)
        classpath(Deps.kotlin_plugin)
    }
}
tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}
