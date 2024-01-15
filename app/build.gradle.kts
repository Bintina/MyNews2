import org.gradle.internal.classpath.Instrumented.systemProperty



plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
}

android {
    namespace = "com.bintina.mynews"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.bintina.mynews"
        minSdk = 29
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
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
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
    testOptions {
        unitTests {
            // Disable StrictMode for instrumented tests
            all {
                systemProperty("org.junit.runners.JUnitCore.defaultResourceName", "JUnit3")
            }
        }
    }
}
dependencies {

    implementation("androidx.core:core-ktx:1.10.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.test.ext:junit-ktx:1.1.5")
    implementation("androidx.test.espresso:espresso-contrib:3.5.1")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("androidx.activity:activity:1.7.2")

//Retrofit implementations
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    //Glide Image dependancy
    implementation("com.github.bumptech.glide:glide:4.15.1")

//coroutine dependencies
    //comes with main dispatcher
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    //comes with the core functions that the language provides
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    //lifecycledependency
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

//Fragments support
    //Java language implementation
    implementation("androidx.fragment:fragment:1.6.2")
    //Kotlin
    implementation("androidx.fragment:fragment-ktx:1.6.2")

    //Gson dependancy
    implementation("com.google.code.gson:gson:2.9.1")

//Esspresso Dependancies............................................................................
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test:rules:1.5.0")
    androidTestImplementation("androidx.test.ext:junit-ktx:1.1.5")
    androidTestImplementation("androidx.test:core-ktx:1.5.0")
    //Intent Dependency
    androidTestImplementation("androidx.test.espresso:espresso-intents:3.5.1")
    testImplementation("org.hamcrest:hamcrest-core:2.2")

    //WorkManager Dependencies......................................................................


    // (Java only)
    implementation("androidx.work:work-runtime:2.8.1")

    // Kotlin + coroutines
    implementation("androidx.work:work-runtime-ktx:2.8.1")

    // optional - RxJava2 support
    implementation("androidx.work:work-rxjava2:2.8.1")

    // optional - GCMNetworkManager support
    implementation("androidx.work:work-gcm:2.8.1")

    // optional - Test helpers
    androidTestImplementation("androidx.work:work-testing:2.8.1")

    // optional - Multiprocess support
    implementation("androidx.work:work-multiprocess:2.8.1")


}
