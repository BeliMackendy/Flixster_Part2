plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.flixster"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.flixster"
        minSdk = 21
        targetSdk = 34
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.asynchttpclient)
    implementation(libs.com.github.bumptech.glide.glide2)
    // Glide v4 uses this new annotation processor -- see https://bumptech.github.io/glide/doc/generatedapi.html
    annotationProcessor(libs.compiler)

    implementation("org.parceler:parceler-api:1.1.12")
    annotationProcessor("org.parceler:parceler:1.1.12")

    implementation ("com.pierfrancescosoffritti.androidyoutubeplayer:core:12.1.1")
}