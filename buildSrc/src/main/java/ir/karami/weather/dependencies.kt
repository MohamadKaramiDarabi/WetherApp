@file:Suppress("unused", "ClassName", "SpellCheckingInspection")

import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.project
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

const val kotlinVersion = "1.6.10"
const val gradleVersion = "7.2.1"

object appConfig {
    const val applicationId = "ir.app.mobin.precence"

    const val compileSdkVersion = 32
    const val buildToolsVersion = "32.0.0"

    const val minSdkVersion = 21
    const val targetSdkVersion = 32
    const val versionCode = 21
    const val versionName = "1.0.6"
}
object deps {
    object kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion"
    }

    object androidx {
        private const val version = "1.4.0"
        const val appCompat = "androidx.appcompat:appcompat:$version"
        const val coreKtx = "androidx.core:core-ktx:1.7.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.2"
        const val material = "com.google.android.material:material:$version"
        const val activityCompose = "androidx.activity:activity-compose:$version"
        const val annotation = "androidx.annotation:annotation:1.3.0"

        const val activityKtx = "androidx.activity:activity-ktx:1.4.0"
        const val media = "androidx.media:media:1.6.0"

        const val customView = "androidx.customview:customview:1.1.0"
    }

    object compose {
        const val version = "1.1.1"

        const val layout = "androidx.compose.foundation:foundation-layout:$version"
        const val foundation = "androidx.compose.foundation:foundation:$version"
        const val ui = "androidx.compose.ui:ui:$version"
        const val material = "androidx.compose.material:material:$version"
        const val materialIconsExtended = "androidx.compose.material:material-icons-extended:$version"
        const val runtime = "androidx.compose.runtime:runtime:$version"
        const val tooling = "androidx.compose.ui:ui-tooling:$version"
        const val compiler = "androidx.compose.compiler:compiler:$version"

        const val navigattion = "androidx.navigation:navigation-compose:2.4.2"
    }

    object lifecycle {
        private const val version = "2.4.1"

        const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version" // viewModelScope
        const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$version" // lifecycleScope
        const val service = "androidx.lifecycle:lifecycle-service:$version"
        const val commonJava8 = "androidx.lifecycle:lifecycle-common-java8:$version"
        const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:2.5.0-rc01"
    }

    object ktor {
        private const val version = "2.0.3"
        const val clientCore = "io.ktor:ktor-client-core:$version"
        const val clientAndroid = "io.ktor:ktor-client-okhttp:$version"
        const val negotiation = "io.ktor:ktor-client-content-negotiation:$version"
        const val serialization = "io.ktor:ktor-serialization-kotlinx-json:$version"
        const val logging = "io.ktor:ktor-client-logging:$version"
    }

    object coroutines {
        private const val version = "1.5.2"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object arrow {
        private const val version = "1.0.1"
        const val core = "io.arrow-kt:arrow-core:$version"
    }

    object daggerHilt {
        const val version = "2.42"
        const val android = "com.google.dagger:hilt-android:$version"
        const val core = "com.google.dagger:hilt-core:$version"
        const val compiler = "com.google.dagger:hilt-compiler:$version"
    }

    object accompanist {
        private const val version = "0.24.8-beta"
        const val swiperefresh = "com.google.accompanist:accompanist-swiperefresh:$version"

        const val placeHolder =  "com.google.accompanist:accompanist-placeholder-material:$version"
        const val pager = "com.google.accompanist:accompanist-pager:$version"


    }

    object coil {
        const val kt = "io.coil-kt:coil:2.0.0"
        const val compose = "io.coil-kt:coil-compose:2.1.0"
    }
    const val viewBindingDelegate = "com.github.hoc081098:ViewBindingDelegate:1.2.0"
    const val flowExt = "io.github.hoc081098:FlowExt:0.1.0"
    const val timber = "com.jakewharton.timber:timber:5.0.1"
    const val logger = "com.orhanobut:logger:2.2.0"

    object test {
        const val junit = "junit:junit:4.13.2"
        const val androidxJunit = "androidx.test.ext:junit:1.1.2"
        const val androidXSspresso = "androidx.test.espresso:espresso-core:3.3.0"

        const val mockk = "io.mockk:mockk:1.12.1"
        const val kotlinJUnit = "org.jetbrains.getKotlin:getKotlin-test-junit:$kotlinVersion"
    }

    const val dataStore = "androidx.datastore:datastore-preferences:1.0.0"

    const val fetch = "androidx.tonyodev.fetch2:xfetch2:3.1.6"

    object exoPlayer {
        private const val version = "2.17.1"
        const val core = "com.google.android.exoplayer:exoplayer-core:$version"
        const val ui = "com.google.android.exoplayer:exoplayer-ui:$version"
    }

    const val workerManagerRuntimeKtx = "androidx.work:work-runtime-ktx:2.7.1"

    const val leakcanary = "com.squareup.leakcanary:leakcanary-android:2.9.1"

    const val sqlitAssetHelper = "com.readystatesoftware.sqliteasset:sqliteassethelper:2.0.1"
}

private typealias PDsS = PluginDependenciesSpec
private typealias PDS = PluginDependencySpec

inline val PDsS.androidApplication: PDS get() = id("com.android.application")
inline val PDsS.androidLib: PDS get() = id("com.android.library")
inline val PDsS.kotlinAndroid: PDS get() = kotlin("android")
inline val PDsS.kotlin: PDS get() = kotlin("jvm")
inline val PDsS.kotlinKapt: PDS get() = kotlin("kapt")
inline val PDsS.daggerHiltAndroid: PDS get() = id("dagger.hilt.android.plugin")
inline val PDsS.androidGradlePlugin: PDS get() = id("com.paranex.gradle")
inline val PDsS.javaLibrary: PDS get() = id("java-library")


object AppModules {
    const val app = ":app"

    object Data {
        inline val DependencyHandler.network get() = project(":data:network")
    }
    object Repository {
        inline val DependencyHandler.weather get() = project(":repository:weather")
    }
}


fun DependencyHandler.implementationHilt() {
    add("implementation", deps.daggerHilt.android)
    add("kapt", deps.daggerHilt.compiler)
}

fun DependencyHandler.implemetationArrow() {
    add("implementation", deps.arrow.core)
}
