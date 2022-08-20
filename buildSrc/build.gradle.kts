repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register("internal-android-plugin") {
            id = "internal-android-plugin"
            implementationClass = "scripts.InternalAndroidPlugin"
        }
    }
}

dependencies {
    implementation(gradleApi())
    implementation(libs.androidGradlePlug)
    implementation(libs.kotlinGradlePlug)

    // workaround for enabling version catalog inside plugins - https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}