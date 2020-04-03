object Classpaths {
    val gradleClasspath = "com.android.tools.build:gradle:${Versions.gradleVersion}"
    val kotlinGradleClasspath =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    val gradleVersionPlugin =
        "com.github.ben-manes:gradle-versions-plugin:${Versions.gradleVersionPluginVersion}"
    val googleServices = "com.google.gms:google-services:${Versions.googleServicesVersion}"
    val fabric = "com.google.firebase:firebase-crashlytics-gradle:${Versions.fabricVersion}"
}