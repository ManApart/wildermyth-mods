//kotlinc -script backup.kts
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption

val modNames = listOf("LOTRNames", "StraightUp", "EngagingEvents")

val paths = File("config.txt").readLines()

val modDirectory = paths
    .map { File(it.split("\\").joinToString(File.separator)) }
    .first { it.exists() }

println("Backing up Files from ${modDirectory.absolutePath}")

modDirectory.listFiles()
    .filter { it.name in modNames }
    .forEach { mod ->
        val dest = Path.of(".", mod.name)
        dest.toFile().mkdirs()
        copyDir(mod.toPath(), dest)
    }

fun copyDir(src: Path, dest: Path) {
    Files.walk(src).filter { !it.toFile().isDirectory }.forEach {
        val destLocation = dest.resolve(src.relativize(it))
        destLocation.toFile().mkdirs()
        Files.copy(it, destLocation, StandardCopyOption.REPLACE_EXISTING)
    }
}