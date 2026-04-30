import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
    kotlin("plugin.serialization") version "1.9.0"
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    jvm()
    
    js {
        browser()
        binaries.executable()
    }
    
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }

    sourceSets {
        val ktorVersion = "3.0.0" // Definimos la versión una sola vez para todos

        androidMain.dependencies {
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.activity.compose)
            // CAMBIO: Antes tenías 2.3.8, DEBE ser ktorVersion (3.0.0)
            implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
        }

        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.jetbrains.navigation3.ui)
            implementation(libs.kotlinx.serialization.core)

            implementation("androidx.lifecycle:lifecycle-viewmodel:2.8.0")
            implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.0")

            // Supabase
            val supabaseVersion = "3.0.1"
            implementation("io.github.jan-tennert.supabase:postgrest-kt:$supabaseVersion")
            implementation("io.github.jan-tennert.supabase:storage-kt:$supabaseVersion")

            // KTOR CORE y PLUGINS (Todos en 3.0.0)
            implementation("io.ktor:ktor-client-core:$ktorVersion")
            implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
            implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
            implementation("io.ktor:ktor-client-logging:$ktorVersion")
            implementation("io.ktor:ktor-client-auth:$ktorVersion")

            // COIL
            implementation("io.coil-kt.coil3:coil-compose:3.0.0-alpha06")
            implementation("io.coil-kt.coil3:coil-network-ktor:3.0.0-alpha06")
        }

        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutinesSwing)
            // CAMBIO: Antes 2.3.8 -> Ahora ktorVersion
            implementation("io.ktor:ktor-client-cio:$ktorVersion")
        }

        val jsMain by getting {
            dependencies {
                // CAMBIO: Antes 2.3.8 -> Ahora ktorVersion
                implementation("io.ktor:ktor-client-js:$ktorVersion")
            }
        }

        val iosMain by creating {
            dependsOn(commonMain.get())
            dependencies {
                // CAMBIO: Antes 2.3.8 -> Ahora ktorVersion
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
            }
        }
    }
}

android {
    namespace = "org.example.project"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "org.example.project"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(libs.compose.uiTooling)
}

compose.desktop {
    application {
        mainClass = "org.example.project.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "org.example.project"
            packageVersion = "1.0.0"
        }
    }
}
