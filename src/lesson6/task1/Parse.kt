@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

import java.lang.IllegalArgumentException
import java.lang.IndexOutOfBoundsException
import java.lang.NumberFormatException

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main() {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
fun dateStrToDigit(str: String): String {
    val dateMaxCorrect: Map<String, Int> = mutableMapOf(
        "января" to 31, "февраля" to 29, "марта" to 31, "апреля" to 30,
        "мая" to 31, "июня" to 30, "июля" to 31, "августа" to 31,
        "сентября" to 30, "октября" to 31, "ноября" to 30, "декабря" to 31
    )
    val dateCorrect: Map<String, Int> = mutableMapOf(
        "января" to 1, "февраля" to 2, "марта" to 3, "апреля" to 4,
        "мая" to 5, "июня" to 6, "июля" to 7, "августа" to 8,
        "сентября" to 9, "октября" to 10, "ноября" to 11, "декабря" to 12
    )
    val parts = str.split(" ")
    if (parts.size != 3) return ""
    else if (!dateMaxCorrect.containsKey(parts[1])) return ""
    else if (parts[0].toInt() > dateMaxCorrect[parts[1]]!!) return ""
    else if (parts[1] == "февраля" &&
        parts[0].toInt() == 29 &&
        parts[2].toInt() % 4 != 0
    ) return ""
    else if (parts[1] == "февраля" &&
        parts[0].toInt() == 29 &&
        parts[2].toInt() % 100 == 0 &&
        parts[2].toInt() % 400 != 0
    ) return ""
    return String.format("%02d.%02d.%02d", parts[0].toInt(), dateCorrect[parts[1]], parts[2].toInt())
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String {
    val dateMaxCorrect: Map<String, Int> = mutableMapOf(
        "января" to 31, "февраля" to 29, "марта" to 31, "апреля" to 30,
        "мая" to 31, "июня" to 30, "июля" to 31, "августа" to 31,
        "сентября" to 30, "октября" to 31, "ноября" to 30, "декабря" to 31
    )
    val dateCorrect: Map<Int, String> = mutableMapOf(
        1 to "января", 2 to "февраля", 3 to "марта", 4 to "апреля",
        5 to "мая", 6 to "июня", 7 to "июля", 8 to "августа",
        8 to "сентября", 10 to "октября", 11 to "ноября", 12 to "декабря"
    )
    val parts = digital.split(".").toMutableList()
    if (parts.size != 3) return ""
    try {
        for (i in parts) i.toInt()
    } catch (e: NumberFormatException) {
        return ""
    }
    if (!dateCorrect.containsKey(parts[1].toInt())) return ""
    else parts[1] = dateCorrect[parts[1].toInt()]!!
    if (parts[0].toInt() > dateMaxCorrect[parts[1]]!!) return ""
    else if (parts[1] == "февраля" &&
        parts[0].toInt() == 29 &&
        parts[2].toInt() % 4 != 0
    ) return ""
    else if (parts[1] == "февраля" &&
        parts[0].toInt() == 29 &&
        parts[2].toInt() % 100 == 0 &&
        parts[2].toInt() % 400 != 0
    ) return ""
    return String.format("%d %s %d", parts[0].toInt(), parts[1], parts[2].toInt())
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -89 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку.
 *
 * PS: Дополнительные примеры работы функции можно посмотреть в соответствующих тестах.
 */
fun flattenPhoneNumber(phone: String): String {
    val checkList: List<Char> = listOf(
        '1', '2', '3', '4', '5',
        '6', '7', '8', '9', '0',
        '+', '-', '(', ')', ' '
    )
    val resList: List<Char> = listOf(
        '1', '2', '3', '4', '5',
        '6', '7', '8', '9', '0',
        '+'
    )
    var result = ""
    if ("()" in phone) return ""
    for (element in phone) {
        if (element !in checkList) return ""
        else if (element in resList) result += element
    }
    return result
}

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    val checkList = listOf(
        '1', '2', '3', '4', '5',
        '6', '7', '8', '9', '0',
        '%', '-', ' '
    )
    var result: Int = -1
    for (i in jumps) if (i !in checkList) return result
    val parts = jumps.split(" ")
    for (part in parts) {
        try {
            if (result < part.toInt()) result = part.toInt()
        } catch (e: NumberFormatException) {
            continue
        }
    }
    return result
}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки, а также в случае отсутствия удачных попыток,
 * вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    val checkList = listOf(
        '1', '2', '3', '4', '5',
        '6', '7', '8', '9', '0',
        '%', '-', '+', ' '
    )
    var result: Int = -1
    for (i in jumps) if (i !in checkList) return result
    val parts = jumps.split(" ")
    for (i in parts.indices step 2) {
        if ("+" in parts[i + 1]) {
            try {
                if (result < parts[i].toInt()) result = parts[i].toInt()
            } catch (e: NumberFormatException) {
                continue
            }
        }
    }
    return result
}

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    val parts = expression.split(" ")
    var result = 0
    val numberList = listOf(
        '1', '2', '3', '4', '5',
        '6', '7', '8', '9', '0'
    )
    val symbolList = listOf(
        "-", "+"
    )
    for (i in parts.indices) {
        if (i % 2 == 0) {
            for (number in parts[i]) if (number !in numberList) throw IllegalArgumentException()
            when {
                i == 0 -> result += parts[i].toInt()
                parts[i - 1] == symbolList[0] -> result -= parts[i].toInt()
                parts[i - 1] == symbolList[1] -> result += parts[i].toInt()
            }
        } else if (i % 2 == 1) {
            if (parts[i] !in symbolList) throw IllegalArgumentException()
        }
    }
    return result
}

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    val words = str.toLowerCase().split(" ")
    if (words.size <= 1) return -1
    for (i in 0 until words.size - 1) {
        if (words[i] == words[i + 1]) {
            var index = 0
            val checkLengthWord = words[i].length
            val indexList = mutableListOf<Int>()
            do {
                indexList.add(str.toLowerCase().indexOf(words[i], index))
                index = indexList.last() + checkLengthWord + 1
            } while (indexList.last() != -1)
            if (indexList.size > 1) {
                for (indx in 0 until indexList.size - 1) {
                    if (indexList[indx] + checkLengthWord + 1 == indexList[indx + 1]) return indexList[indx]
                }
            }
        }
    }
    return -1
}

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше либо равны нуля.
 */
fun mostExpensive(description: String): String {
    val descList = description.split("; ")
    val descMap = mutableMapOf<String, Double>()
    var tmpCost = 0.0
    var result = ""
    for (element in descList) {
        val tmp = element.split(" ")
        try {
            descMap[tmp[0]] = tmp[1].toDouble()
        } catch (e: IndexOutOfBoundsException) {
            return result
        }
    }
    descMap.forEach { (key, value) ->
        if (value < 0) return result
        else if (value > tmpCost) {
            tmpCost = value
            result = key
        }
    }
    return result
}

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int = TODO()

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> = TODO()
