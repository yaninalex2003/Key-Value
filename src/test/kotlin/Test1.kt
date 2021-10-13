import java.io.File
import kotlin.test.*

fun mapToSet(m: MutableMap<String, String>): Set<String> {
    val ans: MutableSet<String> = mutableSetOf()
    for (key in m.keys) {
        ans += "$key==${m[key]}"
    }
    return ans
}

internal class Test1 {

    @Test
    fun testFindValue() {
        assertEquals(null, findValue("1", dataToMap(File("src/testFindValue"))))
        assertEquals("%", findValue("qwe", dataToMap(File("src/testFindValue"))))
    }

    @Test
    fun testAddAndDelete() {
        val dataMap = dataToMap(File("src/testAddAndDelete"))
        add("1", "x", dataMap)
        assertEquals(setOf("0==1", "qwe==%", "1==x"), mapToSet(dataMap))
        add("1", "y", dataMap)
        assertEquals(setOf("0==1", "qwe==%", "1==x"), mapToSet(dataMap))
        delete("1", dataMap)
        assertEquals(setOf("0==1", "qwe==%"), mapToSet(dataMap))
        delete("#", dataMap)
        assertEquals(setOf("0==1", "qwe==%"), mapToSet(dataMap))
    }

    @Test
    fun testChange() {
        val dataMap = dataToMap(File("src/testChange"))
        change("qwe", "rty", dataMap)
        assertEquals(setOf("0==1", "qwe==rty"), mapToSet(dataMap))
        change("2", "Я хочу ботать", dataMap)
        assertEquals(setOf("0==1", "qwe==rty"), mapToSet(dataMap))
        change("qwe", "%", dataMap)
        assertEquals(setOf("0==1", "qwe==%"), mapToSet(dataMap))
    }

    @Test
    fun testSwap() {
        val dataMap = dataToMap(File("src/testSwap"))
        swap("qwe", "0", dataMap)
        assertEquals(setOf("0==%", "qwe==1"), mapToSet(dataMap))
        swap("0", "Я хочу ботать", dataMap)
        assertEquals(setOf("0==%", "qwe==1"), mapToSet(dataMap))
        swap("0", "qwe", dataMap)
        assertEquals(setOf("0==1", "qwe==%"), mapToSet(dataMap))
    }
}
