import java.io.File

fun main(args: Array<String>){
    val gameModsFolder = determineGameModFolder(args)
    val workshopFolder = determineWorkshopFolder(args)
    val nameJsons = getAllNameJsons(workshopFolder)
    val patchedNames = mergeFiles(nameJsons)
    setupPatchMod(gameModsFolder)
    addPatchedFile(gameModsFolder, patchedNames)
}

private fun determineGameModFolder(args: Array<String>): File {
    TODO("Not yet implemented")
}

private fun determineWorkshopFolder(args: Array<String>): File {
    TODO("Not yet implemented")
}

private fun getAllNameJsons(workshopFolder: File): List<Map<String, Any>> {
    TODO("Not yet implemented")
}

fun addPatchedFile(gameModsFolder: File, patchedNames: Map<String, Any>) {
    TODO("Not yet implemented")
}

fun setupPatchMod(gameModsFolder: File) {
    TODO("Not yet implemented")
}
