plugins {
    id("com.android.application")
    kotlin("android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
    kotlin("plugin.serialization") version "1.6.10"
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.6.1")
    implementation("androidx.appcompat:appcompat:1.4.2")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.1.0-alpha03")
    implementation("com.google.accompanist:accompanist-insets:0.19.0")
    implementation("androidx.compose.ui:ui:1.1.1")
    implementation("androidx.compose.material:material:1.1.1")
    implementation("androidx.compose.ui:ui-tooling:1.1.1")

    //pager
    implementation("com.google.accompanist:accompanist-pager:0.19.0") // Pager
    implementation("com.google.accompanist:accompanist-pager-indicators:0.19.0") // Pager Indicators

    implementation("androidx.compose.material:material-icons-extended:1.1.1")
    implementation("androidx.paging:paging-compose:1.0.0-alpha10")
    implementation("androidx.activity:activity-compose:1.5.1")
    implementation("androidx.arch.core:core-testing:2.1.0")
    implementation("com.google.accompanist:accompanist-swiperefresh:0.19.0")
    implementation("com.google.accompanist:accompanist-placeholder-material:0.19.0")
    //UI Systemcolor
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.21.4-beta")

    implementation("androidx.test:core:1.4.0")
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.7")
    kapt("com.google.dagger:hilt-android-compiler:2.42")
    implementation("com.google.dagger:hilt-android:2.42")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    implementation("io.ktor:ktor-client-android:1.6.2")
    implementation("com.jakewharton.timber:timber:5.0.0")


    // Test rules and transitive dependencies:

    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.1.1")
    // Needed for createComposeRule, but not createAndroidComposeRule:
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.1.1")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    testImplementation("androidx.test:core:1.4.0")

    androidTestImplementation("org.mockito:mockito-android:4.1.0")
    //testImplementation ("org.mockito:mockito-inline:2.13.0")
    testImplementation("junit:junit:4.13.2")
    testImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")

    androidTestImplementation("com.google.dagger:hilt-android-testing:2.40.5")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.42")
    androidTestAnnotationProcessor("com.google.dagger:hilt-android-compiler:2.42")
    testImplementation("org.robolectric:robolectric:4.6.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.1.1")
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.1-native-mt")

    //testImplementation("com.google.truth:truth:1.1.3")
    testImplementation("com.squareup.okhttp3:mockwebserver:5.0.0-alpha.2")
    implementation("io.coil-kt:coil-compose:1.3.1")
    implementation("io.coil-kt:coil-gif:1.4.0")
    implementation("androidx.core:core-splashscreen:1.0.0")

}

android {
    compileSdk = 31
    sourceSets["androidTest"].kotlin.srcDir("src/androidTest/assets")
    defaultConfig {
        applicationId = "com.habileducation.themovie.android"
        minSdk = 23
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "com.habileducation.themovie.HiltTestRunner"
    }
    packagingOptions {
        //2 files found with path 'META-INF/AL2.0'.
//        pickFirst("META-INF/AL2.0")
//        pickFirst("META-INF/LGPL2.1")
//        pickFirst("META-INF/licenses/ASM")
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.1"
    }
    kotlinOptions {
        jvmTarget = "11"
        freeCompilerArgs = listOf("-opt-in=kotlin.RequiresOptIn")
    }
    sourceSets {
        getByName("main") {
            resources {
                srcDirs("src/main/resources", "src/androidTest/resources")
            }
        }
    }
}

