/**
 * Data class that Represents a Gradle dependency
 * @param artifact the artifact of the dependency
 * @param version version of the dependency
 *
 * @author Marc Moreno
 */
data class Dependency(
    val artifact: String,
    val version: String
): Comparable<Dependency> {

    override fun toString(): String {
        return "$artifact:$version"
    }

    override fun equals(other: Any?): Boolean {
        return if(other !is Dependency) {
            false
        } else {
            artifact == other.artifact
        }
    }

    override fun compareTo(other: Dependency): Int {
        return version.compareTo(other.version)
    }

    override fun hashCode(): Int {
        return artifact.hashCode()
    }
}


