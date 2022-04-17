import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * Object that contains a set of dependencies and decides witch dependency configuration to use
 * in order to resolve the dependency
 *
 * @param dependencies a set of dependencies for the bucket
 *
 * @author Marc Moreno
 */
sealed class DependencyBucket(val dependencies: Set<Dependency>) {

    /** The configuration of for the dependency handler*/
    abstract val configuration: String

    /**
     * Extension function that allows scoped DependencyHandlers to add the dependencies of this bucket
     *
     * @receiver [DependencyHandler] that will resolve the dependencies of the bucket
     */
    fun DependencyHandler.addDependencies() {
        dependencies.forEach {
            add(configuration, it.toString())
        }
    }
}

/**
 * [DependencyBucket] that will use the implementation configuration
 * @author Marc Moreno
 */
class ImplementationBucket(dependencies: Set<Dependency>) : DependencyBucket(dependencies) {
    constructor(vararg dependencies: Dependency) : this(dependencies.toSet())

    override val configuration: String = "implementation"
}

/**
 * [DependencyBucket] that will use the androidTestImplementation configuration
 * @author Marc Moreno
 */
class AndroidTestsImplementationBucket(dependencies: Set<Dependency>) : DependencyBucket(dependencies) {
    constructor(vararg dependencies: Dependency) : this(dependencies.toSet())

    override val configuration: String = "androidTestImplementation"
}

/**
 * [DependencyBucket] that will use the debugImplementation configuration
 * @author Marc Moreno
 */
class DebugImplementationBucket(dependencies: Set<Dependency>) : DependencyBucket(dependencies) {
    constructor(vararg dependencies: Dependency) : this(dependencies.toSet())

    override val configuration: String = "debugImplementation"
}

/**
 * [DependencyBucket] that will use the testImplementation configuration
 * @author Marc Moreno
 */
class TestImplementationBucket(dependencies: Set<Dependency>) : DependencyBucket(dependencies) {
    constructor(vararg dependencies: Dependency) : this(dependencies.toSet())

    override val configuration: String = "testImplementation"
}