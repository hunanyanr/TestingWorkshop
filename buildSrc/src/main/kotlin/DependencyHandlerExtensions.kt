import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.DependencyHandlerScope

//region Project & DependencyHandler utils
/**
 * Extension function that will scope a [DependencyHandler] under a [DependencyBucket] and will
 * call it's [DependencyBucket.addDependencies] function inside.
 */
fun DependencyHandler.add(dependencyBucket: DependencyBucket) {
    with(dependencyBucket){
        addDependencies()
    }
}

/**
 * Add all de dependencies from a list of buckets
 */
fun Project.buckets(vararg buckets: DependencyBucket) {
    DependencyHandlerScope.of(dependencies).apply {
        buckets.forEach(::add)
    }
}
//endregion