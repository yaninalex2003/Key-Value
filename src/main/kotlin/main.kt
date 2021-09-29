import java.io.File
import java.io.IOException;
import java.io.RandomAccessFile;

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

fun findValue(key: String, data: File): String? {
    val dataMap = dataToMap(data)
    if (key in dataMap.keys) return dataMap[key]
    else {
        print("Такого ключа не существует")
        return null
    }
}

fun add(key: String, value: String, data: File) {
    val dataMap = dataToMap(data)
    if (key in dataMap.keys) {
        println(
            "Такой ключ уже имеется в базе данных, для изменения значения этого ключа" +
                    " можете воспользоваться функцией change"
        )
    } else {
        data.appendText("\n$key==$value")
    }
}

fun delete(key: String, data: File) {
    val dataMap = dataToMap(data)
    if (key in dataMap.keys) {
        dataMap.remove(key)
        val output = StringBuilder()
        for (i in dataMap.keys) {
            output.append("$i==${dataMap[i]}\n")
        }
        data.writeText(output.toString())
    } else {
        println("Такого ключа не существует")
    }
}

fun change(key: String, value: String, data: File) {
    val dataMap = dataToMap(data)
    if (key in dataMap.keys) {
        dataMap[key] = value
        val output = StringBuilder()
        for (i in dataMap.keys) {
            output.append("$i==${dataMap[i]}\n")
        }
        data.writeText(output.toString())
    } else {
        println("Такого ключа еще нет, для создания ключа вы можете использовать функцию add")
    }
}

fun swap(key1: String, key2: String, data: File) {
    val dataMap = dataToMap(data)
    if (key1 !in dataMap.keys) {
        println("Ключ $key1 не существует")
    } else if (key2 !in dataMap.keys) {
        println("Ключ $key2 не существует")
    } else {
        val x = dataMap[key1]
        val y = dataMap[key2]
        dataMap[key1] = y.toString()
        dataMap[key2] = x.toString()
        val output = StringBuilder()
        for (i in dataMap.keys) {
            output.append("$i==${dataMap[i]}\n")
        }
        data.writeText(output.toString())
    }
}

fun main(args: Array<String>) {
    val s = readLine()!!
    val a = s.split("  ")
    if (a[0] == "find") {
        findValue(a[1], File("src/data"))
    }
    if (a[0] == "add") {
        add(a[1], a[2], File("src/data"))
    }
    if (a[0] == "delete") {
        delete(a[1], File("src/data"))
    }
    if (a[0] == "change") {
        change(a[1], a[2], File("src/data"))
    }
    if (a[0] == "swap") {
        swap(a[1], a[2], File("src/data"))
    }
}
