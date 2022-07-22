import ir.karami.weather.buildsrc.implementationCompose
import ir.karami.weather.buildsrc.implementationKtor

plugins {
  androidLib
  kotlinAndroid
  kotlinKapt
  daggerHiltAndroid
  kotlin("plugin.serialization") version kotlinVersion
}
hilt {
  enableExperimentalClasspathAggregation = true
}

kapt {
  correctErrorTypes = true
}

android {
  configureAndroid()
  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_1_8.toString()
  }
}

dependencies {
  implementationKtor()
  implementationHilt()
}