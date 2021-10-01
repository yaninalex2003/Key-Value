import java.io.File
import kotlin.test.*

fun fileToSet(dataTest: File): Set<String> {
    val ans: MutableSet<String> = mutableSetOf()
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
        assertEquals(setOf("0==1", "qwe==%", "1==x"), fileToSet(File("src/testAddAndDelete")))
        add("1", "y", File("src/testAddAndDelete"))
        assertEquals(setOf("0==1", "qwe==%", "1==x"), fileToSet(File("src/testAddAndDelete")))
        delete("1", File("src/testAddAndDelete"))
        assertEquals(setOf("0==1", "qwe==%"), fileToSet(File("src/testAddAndDelete")))
        delete("#", File("src/testAddAndDelete"))
        assertEquals(setOf("0==1", "qwe==%"), fileToSet(File("src/testAddAndDelete")))
    }

    @Test
    fun testChange() {
        change("qwe", "rty", File("src/testChange"))
        assertEquals(setOf("0==1", "qwe==rty"), fileToSet(File("src/testChange")))
        change("2", "Я хочу ботать", File("src/testChange"))
        assertEquals(setOf("0==1", "qwe==rty"), fileToSet(File("src/testChange")))
        change("qwe", "%", File("src/testChange"))
        assertEquals(setOf("0==1", "qwe==%"), fileToSet(File("src/testChange")))
    }

    @Test
    fun testSwap() {
        swap("qwe", "0", File("src/testSwap"))
        assertEquals(setOf("0==%", "qwe==1"), fileToSet(File("src/testSwap")))
        swap("0", "Я хочу ботать", File("src/testSwap"))
        assertEquals(setOf("0==%", "qwe==1"), fileToSet(File("src/testSwap")))
        swap("0", "qwe", File("src/testSwap"))
        assertEquals(setOf("0==1", "qwe==%"), fileToSet(File("src/testSwap")))
    }
}
