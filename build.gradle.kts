buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("https://www.jetbrains.com/intellij-repository/releases")
        maven("https://jetbrains.bintray.com/intellij-third-party-dependencies")
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
        classpath("com.android.tools.build:gradle:7.0.3")
        classpath("com.codingfeline.buildkonfig:buildkonfig-gradle-plugin:0.9.0")
        classpath("com.squareup.sqldelight:gradle-plugin:1.5.0")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.38.1")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}