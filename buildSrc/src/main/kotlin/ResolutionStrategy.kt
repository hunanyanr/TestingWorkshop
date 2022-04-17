/**
 * Interface that represents the strategy to follow when we have version conflicts.
 *
 * The main function returns a [Set] of Dependencies (not allowing duplicates)
 *
 */
interface ResolutionStrategy {
    fun <Deps : Iterable<Dependency>> Deps.resolveConflicts(): Set<Dependency>
}

/**
 * Strategy that selects the maximum version in case of conflict
 */
object ByMaxVersionStrategy : ResolutionStrategy {
    override fun <Deps : Iterable<Dependency>> Deps.resolveConflicts(): Set<Dependency> =
        groupBy { it.artifact }
            .mapValues { it.value.maxByOrNull { it.version } }
            .map { it.value }
            .filterNotNull()
            .toSet()
}

/**
 * Strategy that selects the minimum version in case of conflict
 */
object ByMinVersionStrategy : ResolutionStrategy {
    override fun <Deps : Iterable<Dependency>> Deps.resolveConflicts(): Set<Dependency> =
        groupBy { it.artifact }
            .mapValues { it.value.minByOrNull { it.version } }
            .map { it.value }
            .filterNotNull()
            .toSet()
}

/**
 * Strategy that selects the first found dependency in case of conflict
 */
object ByFirstStrategy : ResolutionStrategy {
    override fun <Deps : Iterable<Dependency>> Deps.resolveConflicts(): Set<Dependency> =
        distinctBy { it.artifact }
            .toSet()
}

/**
 * Strategy that selects the last found version in case of conflict
 */
object ByLastStrategy : ResolutionStrategy {
    override fun <Deps : Iterable<Dependency>> Deps.resolveConflicts(): Set<Dependency> =
        groupBy { it.artifact }
            .mapValues { it.value.lastOrNull() }
            .map { it.value }
            .filterNotNull()
            .toSet()
}