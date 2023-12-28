import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
}

android {
    namespace = "com.bintina.mynews"
    compileSdk = 34

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
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.test.ext:junit-ktx:1.1.5")
    implementation("androidx.test.espresso:espresso-contrib:3.5.1")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


//Retrofit implementations
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    //Glide Image dependancy
    implementation("com.github.bumptech.glide:glide:4.16.0")

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
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation ("androidx.test:runner:1.5.2")
    androidTestImplementation ("androidx.test:rules:1.5.0")
    androidTestImplementation ("androidx.test.ext:junit-ktx:1.1.5")
    androidTestImplementation ("androidx.test:core-ktx:1.5.0")
    //Intent Dependency
    androidTestImplementation ("androidx.test.espresso:espresso-intents:3.5.1")
    testImplementation ("org.hamcrest:hamcrest-core:2.2")

    //WorkManager Dependencies......................................................................


        // (Java only)
        implementation("androidx.work:work-runtime:2.9.0")

        // Kotlin + coroutines
        implementation("androidx.work:work-runtime-ktx:2.9.0")

        // optional - RxJava2 support
        implementation("androidx.work:work-rxjava2:2.9.0")

        // optional - GCMNetworkManager support
        implementation("androidx.work:work-gcm:2.9.0")

        // optional - Test helpers
        androidTestImplementation("androidx.work:work-testing:2.9.0")

        // optional - Multiprocess support
        implementation ("androidx.work:work-multiprocess:2.9.0")

    //ViewModel.....................................................................................
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    // Lifecycles only (without ViewModel or LiveData)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    // Lifecycle utilities for Compose
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")

    // Saved state module for ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.6.2")

    // Annotation processor
    //kapt("androidx.lifecycle:lifecycle-compiler:2.6.2")
    // alternately - if using Java8, use the following instead of lifecycle-compiler
    implementation("androidx.lifecycle:lifecycle-common-java8:2.6.2")

    // optional - helpers for implementing LifecycleOwner in a Service
    implementation("androidx.lifecycle:lifecycle-service:2.6.2")

    // optional - ProcessLifecycleOwner provides a lifecycle for the whole application process
    implementation("androidx.lifecycle:lifecycle-process:2.6.2")

    // optional - ReactiveStreams support for LiveData
    implementation("androidx.lifecycle:lifecycle-reactivestreams-ktx:2.6.2")

    // optional - Test helpers for LiveData
    testImplementation("androidx.arch.core:core-testing:2.2.0")

    // optional - Test helpers for Lifecycle runtime
    testImplementation ("androidx.lifecycle:lifecycle-runtime-testing:2.6.2")


}