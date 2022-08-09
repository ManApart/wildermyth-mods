fun mergeFiles(files: List<Map<String, Any>>): Map<String, Any> {
    val merged = mutableMapOf<String, Any>()
    val keys = files.flatMap { it.keys }.toSet()
    keys.forEach { key ->
        val options = files.mapNotNull { it[key] }
        if (options.first() is List<*>){
            merged[key] = options.flatMap { it as List<*> }.toSet().toList()
        } else {
            merged[key] = options.first()
        }
    }

    return merged
}