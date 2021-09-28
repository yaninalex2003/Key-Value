import java.io.File

fun dataToMap(data: File): MutableMap<String, String> {
    val ans: MutableMap<String, String> = mutableMapOf()
    for (line in data.readLines()) {
        val map1 = line.split("==")
        ans += map1[0] to map1[1]
    }
    return ans;
}

fun findValue(key: String): String? {
    val dataMap = dataToMap(File("src/data"))
    if (key in dataMap.keys) return dataMap[key]
    else {
        print("Такого ключа не существует")
        return null
    }
}

fun add(key: String, value: String) {
    val dataMap = dataToMap(File("src/data"))
    if (key in dataMap.keys) {
        println(
            "Такой ключ уже имеется в базе данных, для изменения значения этого ключа" +
                    " можете воспользоваться функцией change"
        )
    } else {
        File("src/data").appendText("\n$key==$value")
    }
}

fun delete(key: String) {
    val dataMap = dataToMap(File("src/data"))
    if (key in dataMap.keys) {
        dataMap.remove(key)
        val output = StringBuilder()
        for (i in dataMap.keys) {
            output.append("$i==${dataMap[i]}\n")
        }
        File("src/data").writeText(output.toString())
    }
    else{
        println("Такого ключа не существует")
    }
}

fun change(key: String, value: String) {
    val dataMap = dataToMap(File("src/data"))
    if (key in dataMap.keys) {
        dataMap[key] = value
        val output = StringBuilder()
        for (i in dataMap.keys) {
            output.append("$i==${dataMap[i]}\n")
        }
        File("src/data").writeText(output.toString())
    }
}

fun main(args: Array<String>) {
    val s = readLine()!!
    val a = s.split("  ")
    if (a[0] == "find") {
        findValue(a[1])
    }
    if (a[0] == "add") {
        add(a[1], a[2])
    }
    if (a[0] == "delete") {
        delete(a[1])
    }
    if (a[0] == "change") {
        change(a[1], a[2])
    }
}
