const val kotlinVersion = "1.6.20-M1"

private const val ktxVersion = "1.7.0"
private const val appCompatVersion = "1.4.0"
private const val materialVersion = "1.5.0"
private const val lifeCycleVersion = "2.4.0-alpha01"


private const val kotestVersion = "5.0.0.M2"
private const val mockkVersion = "1.12.0"

private const val coroutinesVersion = "1.4.2"

private const val ktorVersion = "2.0.0-beta-1"

private const val arrowVersion = "1.0.1"

val androidXBucket = ImplementationBucket(
    "androidx.core:core-ktx" version ktxVersion,
    "androidx.appcompat:appcompat" version appCompatVersion,
    "com.google.android.material:material" version materialVersion,
    "androidx.constraintlayout:constraintlayout" version materialVersion,
    "androidx.lifecycle:lifecycle-runtime-ktx" version lifeCycleVersion,
    "androidx.lifecycle:lifecycle-viewmodel-ktx" version lifeCycleVersion,
    "androidx.lifecycle:lifecycle-viewmodel-savedstate" version lifeCycleVersion,
    "androidx.activity:activity-ktx" version appCompatVersion
)

val networkBucket = ImplementationBucket(
    "io.ktor:ktor-client-core" version ktorVersion,
    "io.ktor:ktor-client-android" version ktorVersion,
    "io.ktor:ktor-client-cio" version ktorVersion,
    "io.ktor:ktor-client-content-negotiation" version ktorVersion,
    "io.ktor:ktor-serialization-kotlinx-json" version ktorVersion
)

val coroutinesBucket = ImplementationBucket(
    "org.jetbrains.kotlinx:kotlinx-coroutines-core" version coroutinesVersion,
    "org.jetbrains.kotlinx:kotlinx-coroutines-android" version coroutinesVersion
)

val unitTestingBucket = TestImplementationBucket(
    "io.kotest:kotest-framework-engine-jvm" version kotestVersion,
    "io.kotest:kotest-runner-junit5" version kotestVersion,
    "io.kotest:kotest-assertions-core" version kotestVersion,
    "io.kotest:kotest-property" version kotestVersion,
    "io.mockk:mockk" version mockkVersion,
    "org.jetbrains.kotlin:kotlin-reflect" version kotlinVersion,
    "io.mockk:mockk-agent-jvm" version mockkVersion,
    "org.jetbrains.kotlinx:kotlinx-coroutines-test" version coroutinesVersion
)

val functionalBucket = ImplementationBucket(
    "io.arrow-kt:arrow-core" version arrowVersion
)

private const val koinVersion = "3.1.3"
val diBucket = ImplementationBucket(
    "io.insert-koin:koin-core" version koinVersion,
    "io.insert-koin:koin-androidx-compose" version koinVersion,
    "io.insert-koin:koin-android" version koinVersion
)

const val ktlintVersion = "0.42.1"