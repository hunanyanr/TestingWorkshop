import javax.annotation.CheckReturnValue
import kotlin.reflect.full.primaryConstructor

//region Bucket utils
/**
 * Compose a list with two [DependencyBucket] using + operator
 */
operator fun DependencyBucket.plus(bucket: DependencyBucket): List<DependencyBucket> {
    return listOf(this, bucket)
}

/**
 * Create a bucket using the specified dependencies
 *
 * @param dependencies the dependencies to add in the bucket
 * @return the new [DependencyBucket] with the specified dependencies
 *
 * @sample
 * ```
 * val bucket: ImplementationBucket //...
 * val debugBucket: DebugImplementationBucket = bucket.copy()
 * ```
 */
@CheckReturnValue
inline fun <reified To : DependencyBucket> create(dependencies: Set<Dependency>): To {
    val toConstructor = To::class.primaryConstructor
    return toConstructor!!.call(dependencies)
}

/**
 * Creates a copy of a [DependencyBucket] but changing the specified type
 * @receiver The [DependencyBucket] to copy
 * @return The new [DependencyBucket]
 */
@CheckReturnValue
inline fun <reified From: DependencyBucket, reified To : DependencyBucket> From.copy(): To {
    return create(dependencies)
}

/**
 * using the + operator to compose a [DependencyBucket] with extra dependencies.
 * It uses the default [ResolutionStrategy] for conflicting versions.
 *
 * @receiver The [DependencyBucket] that contains the original dependencies
 * @param addedDependencies the dependencies we want to add
 * @param resolutionStrategy [ResolutionStrategy] that will decide wich version to conserve in case of conflicts
 * @return The new [DependencyBucket] containing all the dependencies
 *
 * @sample
 * ```
 *  val bucket: ImplementationBucket //...
 *  val anyDependencies = setOf<Dependency>()
 *  val debugBucket: DebugImplementationBucket = bucket.add(anyDependencies)
 *
 *  or
 *
 *  val debugBucket: DebugBuckegt = bucket.add(anyDependencies, ByFirstStrategy)
 * ```
 */
@CheckReturnValue
inline fun <DB : DependencyBucket, reified To: DependencyBucket> DB.add(
    addedDependencies: Set<Dependency>,
    resolutionStrategy: ResolutionStrategy = ByMaxVersionStrategy
): To {

    val allDependencies = resolutionStrategy.run {
        (dependencies + addedDependencies)
            .resolveConflicts()
    }

    return create(allDependencies)
}

/**
 * Using the + operator to compose a [DependencyBucket] with extra dependencies.
 * It uses the default [ResolutionStrategy] for conflicting versions.
 *
 * @sample
 * ```
 *  val bucket: ImplementationBucket //...
 *  val anyDependencies = setOf<Dependency>()
 *  val debugBucket: DebugImplementationBucket = bucket + anyDependencies
 * ```
 */
@CheckReturnValue
inline operator fun <DB : DependencyBucket, reified To: DependencyBucket> DB.plus(
    addedDependencies: Set<Dependency>): To {
    return this.add(addedDependencies)
}

/**
 * Using the + operator to compose a [DependencyBucket] with a single extra dependency.
 *
 * @sample
 * ```
 *  val bucket: ImplementationBucket //...
 *  val anyDependencies = setOf<Dependency>()
 *  val debugBucket: DebugImplementationBucket = bucket + anyDependencies
 * ```
 */
@CheckReturnValue
inline operator fun <DB : DependencyBucket, reified To: DependencyBucket> DB.plus(
    addedDependency: Dependency
): To {
    return this.add(setOf(addedDependency))
}

/**
 * Removes the specified dependencies from the bucket
 */
@CheckReturnValue
inline operator fun <DB : DependencyBucket, reified To: DependencyBucket> DB.minus(
    removedDependencies: Set<Dependency>): To {
    val newDependencies = dependencies - removedDependencies
    return create(newDependencies.toSet())
}

/**
 * Removes the specified dependency from the bucket
 */
@CheckReturnValue
inline operator fun <DB : DependencyBucket, reified To: DependencyBucket> DB.minus(
    removedDependency: Dependency
): To {
    val newDependencies = dependencies - removedDependency
    return create(newDependencies.toSet())
}

/**
 * Uses a infix fun to compose a Dependency with the artifact and the version
 * @sample
 * ``` val dependency = "artifact" version "1.2.3"```
 */
infix fun String.version(version: String): Dependency {
    return Dependency(this, version)
}
//endregion