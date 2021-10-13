import java.io.File

fun dataToMap(data: File): MutableMap<String, String> {
    val ans: MutableMap<String, String> = mutableMapOf()
    for (line in data.readLines()) {
        if (line.isNotEmpty()) {
            val map1 = line.split("==")
            ans += map1[0] to map1[1]
        }
    }
    return ans
}

fun findValue(key: String, dataMap: MutableMap<String, String>): String? {
    return if (key in dataMap.keys) dataMap[key]
    else {
        print("Ключ $key не существует")
        null
    }
}

fun add(key: String, value: String, dataMap: MutableMap<String, String>){
    if (key in dataMap.keys) {
        println(
            "Ключ $key уже имеется в базе данных, для изменения значения этого ключа" +
                    " можете воспользоваться функцией change"
        )
    } else {
        dataMap += key to value
    }
}

fun delete(key: String, dataMap: MutableMap<String, String>) {
    if (key in dataMap.keys) {
        dataMap.remove(key)
    } else {
        println("Ключ $key не существует")
    }
}

fun change(key: String, value: String, dataMap: MutableMap<String, String>) {
    if (key in dataMap.keys) {
        dataMap[key] = value
    } else {
        println("Ключ $key не существует, для создания ключа вы можете использовать функцию add")
    }
}

fun swap(key1: String, key2: String, dataMap: MutableMap<String, String>) {
    if (key1 !in dataMap.keys) {
        println("Ключ $key1 не существует")
    } else if (key2 !in dataMap.keys) {
        println("Ключ $key2 не существует")
    } else {
        val x = dataMap[key1]
        val y = dataMap[key2]
        dataMap[key1] = y.toString()
        dataMap[key2] = x.toString()
    }
}
fun toFile(dataMap: Map<String, String>, data: File){
    val output = StringBuilder()
    for (i in dataMap.keys) {
        output.append("$i==${dataMap[i]}\n")
    }
    data.writeText(output.toString())
}

fun main() {
    val dataMap = dataToMap(File("src/data"))
    var line = readLine()!!
    while (line!="end") {
        if (line == "find") {
            val key = readLine()!!
            println("Значение ключа $key: ${findValue(key, dataMap)}")
        } else if (line == "add") {
            val key = readLine()!!
            val value = readLine()!!
            add(key, value, dataMap)
        } else if (line == "delete") {
            val key = readLine()!!
            delete(key, dataMap)
        } else if (line == "change") {
            val key = readLine()!!
            val newValue = readLine()!!
            change(key, newValue, dataMap)
        } else if (line == "swap") {
            val key1 = readLine()!!
            val key2 = readLine()!!
            swap(key1, key2, dataMap)
        } else {
            println(
                "К сожалению, такой функции не существует, существующие функции вы можете" +
                        " посмотреть в документации"
            )
        }
        line = readLine()!!
    }
    toFile(dataMap, File("src/data"))
}
