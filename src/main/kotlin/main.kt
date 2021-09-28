import java.io.File

fun scanFile(name: String): Array<String> {
    var text: Array<String> = arrayOf()
    for (line in File(name).readLines())
        text += line
    return text
}

fun dataToMap(data: File): MutableMap<String, String> {
    val ans: MutableMap<String, String> = mutableMapOf()
    for (line in data.readLines()) {
        val map1 = line.split("===")
        ans += map1[0] to map1[1]
    }
    return ans;
}

fun find(key: String): String? {
    val dataMap = dataToMap(File("src/data"))
    if (key in dataMap.keys) return dataMap[key]
    else {
        print("Такого ключа не существует")
        return null
    }
}

fun plus(key: String, value: String) {
    val writer = File("src/data").bufferedWriter()
    val dataMap = dataToMap(File("src/data"))
    if (key in dataMap.values) {
        println(
            "Такой ключ уже имеется в базе данных, для изменения значения этого ключа" +
                    " можете воспользоваться функцией change"
        )
    } else {
        writer.write("$key===$value")
    }
    writer.close()
}

fun delete(key: String) {
    val dataMap = dataToMap(File("src/data"))
    println("Ключ: $key со значением ${dataMap[key]} удален")
    dataMap.remove(key)
    val writer = File("src/data").bufferedWriter()
    for (i in dataMap.keys) {
        writer.write("$i===${dataMap[i]}")
    }
    writer.close()
}

fun change(key: String, value: String) {
    val dataMap = dataToMap(File("src/data"))
    if (key in dataMap.keys) {
        dataMap[key]=value
        val writer = File("src/data").bufferedWriter()
        writer.write("$key===$value")
        writer.close()
    }
}

fun main(args: Array<String>) {
    TODO()
}
