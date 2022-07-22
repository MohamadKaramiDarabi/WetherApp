import AppModules.Repository.weather
import ir.karami.weather.buildsrc.implementationCompose

plugins {
    androidApplication
    kotlinAndroid
    kotlinKapt
    daggerHiltAndroid
}
hilt {
    enableExperimentalClasspathAggregation = true
}
kapt {
    correctErrorTypes = true
}

android {
    configureAndroid()
    configureCompose()

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = freeCompilerArgs + "-Xskip-prerelease-check"
    }
}

dependencies {
    implementation(deps.kotlin.stdlib)
    implementation(deps.androidx.coreKtx)
    implementation(deps.androidx.appCompat)
    implementation(deps.androidx.material)
    implementationCompose()
    implementationHilt()
    implementation(weather)
}