import kotlin.test.Test
import kotlin.test.assertEquals

class MergeTest {

    @Test
    fun singleFile() {
        val input = listOf(mapOf("A" to "B"))

        val actual = mergeFiles(input)

        assertEquals(input.first(), actual)
    }

    @Test
    fun simpleAddToArray() {
        val input = listOf(
            mapOf("A" to listOf(1, 2)),
            mapOf("A" to listOf(3)),
        )

        val expected = mapOf("A" to listOf(1, 2, 3))

        val actual = mergeFiles(input)
        assertEquals(expected, actual)
    }

    @Test
    fun nonArrayNotTouched() {
        val input = listOf(
            mapOf("A" to listOf(4), "B" to false),
            mapOf("A" to listOf(3)),
        )

        val expected = mapOf("A" to listOf(4, 3), "B" to false)

        val actual = mergeFiles(input)
        assertEquals(expected, actual)
    }

    @Test
    fun allPropertiesPreserved() {
        val input = listOf(
            mapOf("A" to listOf(4)),
            mapOf("A" to listOf(3), "B" to true),
        )

        val expected = mapOf("A" to listOf(4, 3), "B" to true)

        val actual = mergeFiles(input)
        assertEquals(expected, actual)
    }

    @Test
    fun uniqueValuesOnly() {
        val input = listOf(
            mapOf("A" to listOf(1, 2, 3)),
            mapOf("A" to listOf(3)),
        )

        val expected = mapOf("A" to listOf(1, 2, 3))

        val actual = mergeFiles(input)
        assertEquals(expected, actual)
    }

    @Test
    fun threeFiles() {
        val input = listOf(
            mapOf("A" to listOf(1, 2)),
            mapOf("A" to listOf(4)),
            mapOf("A" to listOf(5)),
        )

        val expected = mapOf("A" to listOf(1, 2, 4, 5))

        val actual = mergeFiles(input)
        assertEquals(expected, actual)
    }

    @Test
    fun noPrioritySystem() {
        //Eventually it'd make sense to have a priority system but I'll call that out of scope for now
        val input = listOf(
            mapOf("A" to "first"),
            mapOf("A" to "second"),
        )

        val expected = mapOf("A" to "first")

        val actual = mergeFiles(input)
        assertEquals(expected, actual)
    }
}