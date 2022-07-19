import ir.karami.weather.buildsrc.implementationCompose

plugins {
    androidApplication
    kotlinAndroid
    kotlinKapt
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
    implementation("androidx.compose.ui:ui-tooling-preview:1.1.1")
    debugImplementation("androidx.compose.ui:ui-tooling:1.1.1")
    implementationCompose()
}