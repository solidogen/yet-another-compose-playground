import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath(libs.androidGradlePlug)
        classpath(libs.kotlinGradlePlug)
    }
}

subprojects {
    tasks.withType<KotlinCompile>().all {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
            apiVersion = "1.6"
            languageVersion = "1.7"
        }
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
