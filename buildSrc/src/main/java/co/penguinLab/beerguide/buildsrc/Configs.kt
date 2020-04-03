object Configs {
    val applicationId = "com.penguinlab.kriptotracker"
    val compileSdkVersion = 29
    val minSdkVersion = 19
    val targetSdkVersion = 29
    val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    val versionCode = calculateVersionCode()
    val versionName = calculateVersionName()
    private val versionMajor = 1
    private val versionMinor = 0
    private val versionPatch = 1

    private fun calculateVersionCode(): Int =
        versionMajor * 1000000 + versionMinor * 10000 + versionPatch * 100

    private fun calculateVersionName(): String = "${versionMajor}.${versionMinor}.${versionPatch}"
}