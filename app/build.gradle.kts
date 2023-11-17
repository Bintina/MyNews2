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
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


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
    implementation("androidx.fragment:fragment:1.6.1")
    //Kotlin
    implementation("androidx.fragment:fragment-ktx:1.6.1")

    //Gson dependancy
    implementation("com.google.code.gson:gson:2.8.2")


}