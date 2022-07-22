import AppModules.Data.network

plugins {
  androidLib
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
  kotlinOptions {
    jvmTarget = "1.8"
  }
}

dependencies {
  api(network)
  implementationHilt()
  implementation(deps.coroutines.core)
}