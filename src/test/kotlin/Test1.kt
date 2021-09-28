import java.io.File
import kotlin.test.*

fun fileToArray(dataTest: File): Array<String> {
    var ans: Array<String> = arrayOf()
    for (line in dataTest.readLines()) {
        if (line.isNotEmpty()) {
            ans += line
        }
    }
    return ans
}

internal class Test1 {

    @Test
    fun testFindValue() {
        assertEquals(null, findValue("1", File("src/testFindValue")))
        assertEquals("%", findValue("qwe", File("src/testFindValue")))
    }

    @Test
    fun testAddAndDelete() {
        add("1", "x", File("src/testAddAndDelete"))
        assertContentEquals(arrayOf("0==1", "qwe==%", "1==x"), fileToArray(File("src/testAddAndDelete")))
        add("1", "y", File("src/testAddAndDelete"))
        assertContentEquals(arrayOf("0==1", "qwe==%", "1==x"), fileToArray(File("src/testAddAndDelete")))
        delete("1", File("src/testAddAndDelete"))
        assertContentEquals(arrayOf("0==1", "qwe==%"), fileToArray(File("src/testAddAndDelete")))
        delete("#", File("src/testAddAndDelete"))
        assertContentEquals(arrayOf("0==1", "qwe==%"), fileToArray(File("src/testAddAndDelete")))
    }

    @Test
    fun testChange() {
        change("qwe", "rty", File("src/testChange"))
        assertContentEquals(arrayOf("0==1", "qwe==rty"), fileToArray(File("src/testChange")))
        change("2", "Я хочу ботать", File("src/testChange"))
        assertContentEquals(arrayOf("0==1", "qwe==rty"), fileToArray(File("src/testChange")))
        change("qwe", "%", File("src/testChange"))
        assertContentEquals(arrayOf("0==1", "qwe==%"), fileToArray(File("src/testChange")))
    }
}
