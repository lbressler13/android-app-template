import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("org.jlleitschuh.gradle.ktlint") version "12.1.1"
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
        minSdk = 34
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

    sourceSets.getByName("testDev") {
        java.setSrcDirs(listOf("src/testDev/kotlin"))
    }

    sourceSets.getByName("testFinal") {
        java.setSrcDirs(listOf("src/testFinal/kotlin"))
    }

    sourceSets.getByName("androidTest") {
        java.setSrcDirs(listOf("src/androidTest/kotlin"))
    }

    sourceSets.getByName("androidTestDev") {
        java.setSrcDirs(listOf("src/androidTestDev/kotlin"))
    }

    sourceSets.getByName("androidTestFinal") {
        java.setSrcDirs(listOf("src/androidTestFinal/kotlin"))
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    testOptions {
        animationsDisabled = true
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
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlin {
        compilerOptions {
            jvmTarget = JvmTarget.fromTarget("11")
        }
    }
}

dependencies {
    val kotlinVersion: String by rootProject.extra

    val androidxCoreVersion = "1.18.0"
    val appCompatVersion = "1.7.1"
    val constraintLayoutVersion = "2.2.1"
    val kotlinUtilsVersion = "1.3.4"
    val lifecycleVersion = "2.10.0"
    val navigationVersion = "2.9.8"

    val androidxJunitVersion = "1.3.0"
    val androidxTestRulesVersion = "1.7.0"
    val espressoVersion = "3.7.0"
    val robolectricVersion = "4.16.1"

    implementation("androidx.core:core-ktx:$androidxCoreVersion")
    implementation("androidx.appcompat:appcompat:$appCompatVersion")
    implementation("androidx.constraintlayout:constraintlayout:$constraintLayoutVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.navigation:navigation-fragment-ktx:$navigationVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navigationVersion")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion")
    implementation("xyz.lbres:kotlin-utils:$kotlinUtilsVersion")

    // testing
    testImplementation(kotlin("test"))
    testImplementation("androidx.test.espresso:espresso-core:$espressoVersion")
    testImplementation("androidx.test.espresso:espresso-intents:$espressoVersion")
    testImplementation("androidx.test.espresso:espresso-contrib:$espressoVersion")
    testImplementation("androidx.test.ext:junit-ktx:$androidxJunitVersion")
    testImplementation("org.robolectric:robolectric:$robolectricVersion")
}

configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
    version.set("0.49.1")
    additionalEditorconfig.set(mapOf("max_line_length" to "120"))
}
