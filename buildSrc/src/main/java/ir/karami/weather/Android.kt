import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.provideDelegate

typealias AndroidBaseExtension = BaseExtension

fun Project.configureAndroid() = this.extensions.getByType<AndroidBaseExtension>().run {
  this.setCompileSdkVersion(appConfig.compileSdkVersion)
  defaultConfig {
    vectorDrawables.useSupportLibrary = true
    renderscriptSupportModeEnabled = true
    minSdk = appConfig.minSdkVersion
    setTargetSdkVersion(appConfig.targetSdkVersion)
    versionCode = appConfig.versionCode
    versionName = appConfig.versionName
    testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
  }
  buildTypes {

    val WEATHER_API_KEY: String by project
    val WEATHER_API_HOST_NAME: String by project

    getByName("release") {
      if (project.name == "network") {
        buildConfigField("String", "WEATHER_API_KEY", WEATHER_API_KEY)
        buildConfigField("String", "WEATHER_API_HOST_NAME", WEATHER_API_HOST_NAME)
      }
      isMinifyEnabled = true
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
      )
    }

    getByName("debug") {
      if (project.name == "network") {
        buildConfigField("String", "WEATHER_API_KEY", WEATHER_API_KEY)
        buildConfigField("String", "WEATHER_API_HOST_NAME", WEATHER_API_HOST_NAME)
      }
      if (project.name == "app") {
        applicationIdSuffix = ".debug"
        versionNameSuffix = "-debug"
      }
      isMinifyEnabled = false
      isDebuggable = true
      isTestCoverageEnabled = true
    }
  }

  productFlavors {
    create("bazaar") {
      flavorDimensions("default")
      resValue("string", "distribution_channel", "bazaar")
    }
    create("googleplay") {
      flavorDimensions("default")
      resValue("string", "distribution_channel", "googleplay")
    }
    create("direct") {
      flavorDimensions("default")
      resValue("string", "distribution_channel", "direct")
    }
  }

  buildFeatures.apply {
    buildConfig = true
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  packagingOptions {
    resources {
      excludes.add("META-INF/rxjava.properties")
      excludes.add("META-INF/DEPENDENCIES")
    }
  }
}

fun Project.configureCompose() =
  this.extensions.getByType<AndroidBaseExtension>().run {
    buildFeatures.apply {
      compose = true
    }

    composeOptions {
      kotlinCompilerExtensionVersion = deps.compose.version
    }
  }

