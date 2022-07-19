repositories {
  mavenCentral()
  google()
}
plugins {
  `kotlin-dsl`
  `kotlin-dsl-precompiled-script-plugins`
}
object Versions {
  const val gradleVersion = "7.2.1"
  const val kotlinVersion = "1.6.10"
}
dependencies {
  implementation("com.android.tools.build:gradle:${Versions.gradleVersion}")
  implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}")
  implementation("com.squareup:javapoet:1.13.0")
  implementation(gradleApi())
  implementation(localGroovy())
}