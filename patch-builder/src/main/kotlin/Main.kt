import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.File

val mapper = jacksonObjectMapper()

data class NameJson(val modName: String, val path: String, val data: Map<String, Any>)

@JsonIgnoreProperties(ignoreUnknown = true)
data class ModInfo(val name: String)

fun main(args: Array<String>) {
    val gameModsFolder = determineGameModFolder(args)
    val workshopFolder = determineWorkshopFolder(args)
    val nameJsons = getAllNameJsons(workshopFolder, workshopFolder)
    if (nameJsons.size > 1) {
        val patchedNames = mergeFiles(nameJsons.map { it.data })
        val modFolder = File(gameModsFolder.absolutePath + File.separator + "user" + File.separator + "PatchMerge")
        setupPatchMod(modFolder, nameJsons)
        addPatchedFile(modFolder, patchedNames)
    } else {
        println("No mods found to merge")
    }
}

private fun determineGameModFolder(args: Array<String>): File {
    val default = "C:\\Program Files\\Steam\\steamapps\\common\\Wildermyth\\mods"
    val options = (args + listOf(default)).map { File(it) }
    return options.firstOrNull { file ->
        file.exists() && file.listFiles().any { it.name == "user" }
    } ?: throw IllegalArgumentException("Unable to find a valid path to the game mods folders. Should be something like $default")
}

private fun determineWorkshopFolder(args: Array<String>): File {
    val default = "C:\\Program Files\\Steam\\steamapps\\workshop\\content\\763890"
    val options = (args + listOf(default)).map { File(it) }
    return options.firstOrNull { file ->
        file.exists() && file.listFiles().any { it.path.contains("workshop") }
    } ?: throw IllegalArgumentException("Unable to find a valid path to the game mods folders. Should be something like $default")
}

private fun getAllNameJsons(workshopFolder: File, file: File): List<NameJson> {
    return when {
        file.isDirectory -> file.listFiles().flatMap { getAllNameJsons(workshopFolder, it) }
        file.name.endsWith("human.names.json") -> {
            //Get the modFolder based on the mod we're looking at
            val modInfoFile = File(workshopFolder.listFiles().first { file.name.contains(it.name) }.path + File.separator + "mod.json")
            val modInfo: ModInfo = mapper.readValue(modInfoFile)
            listOf(NameJson(modInfo.name, file.absolutePath, mapper.readValue(file)))
        }

        else -> listOf()
    }
}

fun setupPatchMod(modFolder: File, nameJsons: List<NameJson>) {
    modFolder.mkdirs()
    val modNames = nameJsons.map { it.modName }
    File(modFolder.path + File.separator + "mod.json").writeText(modInfo(modNames))
}

fun addPatchedFile(gameModsFolder: File, patchedNames: Map<String, Any>) {

//    val path = listOf(gameModsFolder.path, ).joinToString(File.separator)
//    File(path).mkdirs()
//    File(path + File.separator + "human.names.json").writeText(mapper.writeValueAsString(patchedNames))
}


private fun modInfo(modNames: List<String>) = """
{
    "author": "Manapart",
    "name": "Patch Merge",
    "blurb": "Merges the names.json for ${modNames.joinToString()}",
    "visibility": "Public",
    "tags": [ "Miscellaneous"],
    "url": "https://wildermyth.com/wiki/index.php"
}
"""