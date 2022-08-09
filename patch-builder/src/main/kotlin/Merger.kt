fun mergeFiles(files: List<Map<String, Any>>): Map<String, Any> {
    val merged = mutableMapOf<String, Any>()
    val keys = files.flatMap { it.keys }.toSet()
    keys.forEach { key ->
        val options = files.mapNotNull { it[key] }
        if (options.first() is List<*>) {
            val flatOptions = options.flatMap { it as List<*> }
            val blankCount = options.maxOfOrNull { option -> (option as List<*>).count { it is String && it.isBlank() } - 1 } ?: 0
            val blanks = if (blankCount > 0) (0 until blankCount).map { "" } else listOf()
            merged[key] = (flatOptions.toSet().toList() + blanks)
        } else {
            merged[key] = options.first()
        }
    }

    return merged
}