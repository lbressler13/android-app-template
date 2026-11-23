import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("com.android.application")
    id("kotlin-android")
    alias(libs.plugins.ktlint)
}

val githubUsername: String? = project.findProperty("github.username")?.toString() ?: System.getenv("USERNAME")
val githubPassword: String? = project.findProperty("github.token")?.toString() ?: System.getenv("ACCESS_TOKEN")

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
    compileSdk = 36

    defaultConfig {
        applicationId = "xyz.lbres.androidapptemplate"
        minSdk = 33
        targetSdk = 36
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
                "proguard-rules.pro",
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

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    testOptions {
        animationsDisabled = true
        // resolves mockk issue: https://github.com/mockk/mockk/issues/297
        packaging {
            jniLibs {
                useLegacyPackaging = true
            }
        }
        unitTests {
            // needed for robolectric
            isIncludeAndroidResources = true

            all { test ->
                test.useJUnit {
                    val testType: String? by project
                    when (testType?.lowercase()) {
                        "unit" -> excludeCategories("org.robolectric.Robolectric")
                        "robolectric" -> includeCategories("org.robolectric.Robolectric")
                    }
                }
                test.maxHeapSize = "1024m"
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    kotlin {
        compilerOptions {
            jvmTarget = JvmTarget.fromTarget("21")
        }
    }
}

dependencies {
    implementation(libs.androidx.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.lifecycle.livedata)
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.custom.kotlinutils)

    // testing
    testImplementation(kotlin("test"))
    testImplementation(libs.espresso.core)
    testImplementation(libs.espresso.contrib)
    testImplementation(libs.espresso.intents)
    testImplementation(libs.junit.ktx)
    testImplementation(libs.robolectric)
}

configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
    version.set("0.49.1")
    additionalEditorconfig.set(mapOf("max_line_length" to "120"))
}
