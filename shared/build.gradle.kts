import ApiProperties.ApiKey
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    kotlin("plugin.serialization") version "1.5.30"
    id("com.squareup.sqldelight")
    id("com.codingfeline.buildkonfig")
}

version = "1.0"

kotlin {
    android(){

    }

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = when {
        System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
        System.getenv("NATIVE_ARCH")?.startsWith("arm") == true -> ::iosSimulatorArm64
        else -> ::iosX64
    }

    iosTarget("ios") {}

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        frameworkName = "shared"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = false
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-core:1.6.2")
                implementation("io.ktor:ktor-client-serialization:1.6.2")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.2.1")
                implementation("org.jetbrains.kotlin:kotlin-serialization:1.5.30")
                implementation("io.ktor:ktor-client-mock:1.6.2")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
                implementation("co.touchlab:stately-common:1.1.1")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.1-native-mt")
                implementation("com.squareup.sqldelight:runtime:1.5.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation(kotlin("test-common"))
                implementation("org.jetbrains.kotlin:kotlin-serialization:1.5.30")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
                implementation("io.ktor:ktor-client-mock:1.6.2")
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-android:1.6.2")
                implementation("com.squareup.sqldelight:android-driver:1.5.1")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation("androidx.test:runner:1.4.0")
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-ios:1.6.2")
                implementation("com.squareup.sqldelight:native-driver:1.5.0")
            }
        }
        val iosTest by getting
    }
}

android {
    compileSdk = 31
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 23
        targetSdk = 30
    }
    buildTypes {
        debug {
            isMinifyEnabled = false
        }

        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

buildkonfig {
    packageName = "com.habileducation.themovie"
    objectName = "AppKey"
    defaultConfigs {
        buildConfigField(STRING, "BASE_URL", ApiKey.BASE_URL)
        buildConfigField(STRING, "LATEST_MOVIES", ApiKey.LATEST_MOVIES)
        buildConfigField(STRING, "POPULAR_MOVIES", ApiKey.POPULAR_MOVIES)
        buildConfigField(STRING, "TOP_RATED_MOVIES", ApiKey.TOP_RATED_MOVIES)
        buildConfigField(STRING, "UPCOMING_MOVIES", ApiKey.UPCOMING_MOVIES)
        buildConfigField(STRING, "NOW_PLAYING_MOVIES", ApiKey.NOW_PLAYING_MOVIES)
        buildConfigField(STRING, "MOVIE_DETAIL", ApiKey.MOVIE_DETAIL)
        buildConfigField(STRING, "MOVIE_REVIEW", ApiKey.MOVIE_REVIEW)
    }
}

sqldelight {
    database("AppDatabase") {
        packageName = "com.habileducation.movie.data.source"
        sourceFolders = listOf("sqldelight")
    }
}