plugins {
    id("com.android.application")
    kotlin("android")
    id("internal-android-plugin")
}

dependencies {
    implementation(libs.coreKtx)
    implementation(libs.composeUi)
    implementation(libs.composeMaterial3)
    implementation(libs.composeUiToolingPreview)
    implementation(libs.lifecycleRuntimeKtx)
    implementation(libs.activityCompose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidxTestExtJunit)
    androidTestImplementation(libs.espressoCore)
    androidTestImplementation(libs.composeUiTestJunit)
    androidTestImplementation(libs.composeUiTooling)
    androidTestImplementation(libs.composeUiTestManifest)
}