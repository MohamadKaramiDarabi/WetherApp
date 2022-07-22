package ir.karami.weather.buildsrc

import com.android.build.gradle.api.AndroidBasePlugin
import deps
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler

internal fun Project.containsAndroidPlugin(): Boolean {
    return project.plugins.toList().any { plugin -> plugin is AndroidBasePlugin }
}

fun DependencyHandler.implementationCompose() {
    arrayOf(
        deps.androidx.activityCompose,
        deps.lifecycle.viewModelCompose,
        deps.compose.layout,
        deps.compose.foundation,
        deps.compose.ui,
        deps.compose.material,
        deps.compose.materialIconsExtended,
        deps.compose.runtime,
        deps.compose.compiler,
        deps.compose.navigattion
    ).forEach { add("implementation", it) }

    add("debugImplementation", deps.compose.tooling)
    add("debugImplementation", deps.kotlin.reflect)
}

fun DependencyHandler.addUnitTest(testImplementation: Boolean = true) {
    val configName = if (testImplementation) "testImplementation" else "implementation"
    add(configName, deps.test.junit)
    add(configName, deps.test.mockk)
    add(configName, deps.test.kotlinJUnit)
    add(configName, deps.coroutines.test)
}

fun DependencyHandler.implementationKtor() {
    arrayOf(
        deps.ktor.clientAndroid,
        deps.ktor.clientCore,
        deps.ktor.negotiation,
        deps.ktor.logging,
        deps.ktor.serialization
    ).forEach { add("implementation", it) }
}
