package scripts

import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.the
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.kotlin.dsl.findByType

class InternalAndroidPlugin : Plugin<Project> {

    private lateinit var project: Project
    private val libs by lazy { project.the<LibrariesForLibs>() }

    override fun apply(target: Project) {
        project = target
        with(project) {
            setupPlugins()
            setupCommonAndroid()
            setupAndroidApplication()
        }
    }

    private fun Project.setupPlugins() {
        plugins.apply("kotlin-android")
    }

    private fun Project.setupCommonAndroid() {
        extensions.findByType<BaseExtension>()?.run {
            setupAndroidDefaultConfig()
//            setupCommonFlavors() // can be done here if needed
        }
    }

    private fun BaseExtension.setupAndroidDefaultConfig() {
        compileSdkVersion(libs.versions.compileSdk.get().toInt())

        defaultConfig {
            minSdk = libs.versions.minSdk.get().toInt()
            targetSdk = libs.versions.targetSdk.get().toInt()

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

            consumerProguardFiles("consumer-rules.pro")

            vectorDrawables {
                useSupportLibrary = true
            }

            packagingOptions {
                resources {
                    excludes += "/META-INF/{AL2.0,LGPL2.1}"
                }
            }
        }
    }

    private fun Project.setupAndroidApplication() {
        plugins.withId("com.android.application") {
            extensions.findByType<BaseAppModuleExtension>()?.run {
                defaultConfig {
                    applicationId = libs.versions.applicationId.get()
                    versionCode = 1
                    versionName = "0.0.1"
                }
                buildTypes {
                    getByName("release") {
                        isMinifyEnabled = false
                        proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            "proguard-rules.pro"
                        )
                    }
                }
                buildFeatures {
                    compose = true
                }
                composeOptions {
                    kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
                }
            }
        }
    }
}