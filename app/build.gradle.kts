plugins {
    id("com.android.application")
    id("kotlin-android")
    id("org.jlleitschuh.gradle.ktlint") version "10.3.0" // ktlint
}

val githubUsername: String? = project.findProperty("gpr.user")?.toString() ?: System.getenv("USERNAME")
val githubPassword: String? = project.findProperty("gpr.key")?.toString() ?: System.getenv("TOKEN")

repositories {
    // general repositories
    google()
    mavenCentral()

    // kotlin utils
    maven {
        url = uri("https://maven.pkg.github.com/lbressler13/kotlin-utils")
        credentials {
            username = githubUsername
            password = githubPassword
        }
    }
}

android {
    namespace = "xyz.lbres.androidapptemplate"
    compileSdk = 33

    defaultConfig {
        applicationId = "xyz.lbres.androidapptemplate"
        minSdk = 29 // maximum sdk available in tester used in github actions
        targetSdk = 33
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments["clearPackageData"] = "true"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions += "type"
    productFlavors {
        create("dev") {
            dimension = "type"
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
        }

        create("final") {
            dimension = "type"
            versionNameSuffix = "-final"
        }
    }

    sourceSets.getByName("main") {
        java.setSrcDirs(listOf("src/main/kotlin"))
    }

    sourceSets.getByName("dev") {
        java.setSrcDirs(listOf("src/dev/kotlin"))
    }

    sourceSets.getByName("final") {
        java.setSrcDirs(listOf("src/final/kotlin"))
    }

    sourceSets.getByName("test") {
        java.setSrcDirs(listOf("src/test/kotlin"))
    }

    sourceSets.getByName("androidTest") {
        kotlin.setSrcDirs(listOf("src/espresso/kotlin"))
        java.setSrcDirs(listOf("src/espresso/kotlin"))
    }

    sourceSets.getByName("androidTestDev") {
        kotlin.setSrcDirs(listOf("src/espressoDev/kotlin"))
        java.setSrcDirs(listOf("src/espressoDev/kotlin"))
    }

    sourceSets.getByName("androidTestFinal") {
        kotlin.setSrcDirs(listOf("src/espressoFinal/kotlin"))
        java.setSrcDirs(listOf("src/espressoFinal/kotlin"))
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    testOptions {
        animationsDisabled = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    val androidxCoreVersion = "1.8.0"
    val androidxLegacyVersion = "1.0.0"
    val appCompatVersion = "1.4.1"
    val constraintLayoutVersion = "2.1.4"
    val kotlinUtilsVersion = "0.4.0"
    val lifecycleVersion = "2.5.1"
    val materialVersion = "1.6.1"
    val navigationVersion = "2.5.3"

    val androidxJunitVersion = "1.1.4"
    val androidxTestVersion = "1.5.0"
    val espressoVersion = "3.5.0"
    val junitVersion = "4.13.2"

    implementation("androidx.appcompat:appcompat:$appCompatVersion")
    implementation("androidx.constraintlayout:constraintlayout:$constraintLayoutVersion")
    implementation("androidx.core:core-ktx:$androidxCoreVersion")
    implementation("androidx.legacy:legacy-support-v4:$androidxLegacyVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.navigation:navigation-fragment-ktx:$navigationVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navigationVersion")
    implementation("com.google.android.material:material:$materialVersion")
    implementation("xyz.lbres:kotlin-utils:$kotlinUtilsVersion")

    // testing
    testImplementation("junit:junit:$junitVersion")
    androidTestImplementation("androidx.test.ext:junit:$androidxJunitVersion")
    androidTestImplementation("androidx.test:rules:$androidxTestVersion")
    androidTestImplementation("androidx.test:runner:$androidxTestVersion") // needed to run on emulator
    androidTestImplementation("androidx.test.espresso:espresso-core:$espressoVersion")
    androidTestImplementation("androidx.test.espresso:espresso-contrib:$espressoVersion")
    androidTestImplementation("androidx.test.espresso:espresso-intents:$espressoVersion")
}
