@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson5.task1


/**
 * Пример
 *
 * Для заданного списка покупок `shoppingList` посчитать его общую стоимость
 * на основе цен из `costs`. В случае неизвестной цены считать, что товар
 * игнорируется.
 */
fun shoppingListCost(
    shoppingList: List<String>,
    costs: Map<String, Double>
): Double {
    var totalCost = 0.0

    for (item in shoppingList) {
        val itemCost = costs[item]
        if (itemCost != null) {
            totalCost += itemCost
        }
    }

    return totalCost
}

/**
 * Пример
 *
 * Для набора "имя"-"номер телефона" `phoneBook` оставить только такие пары,
 * для которых телефон начинается с заданного кода страны `countryCode`
 */
fun filterByCountryCode(
    phoneBook: MutableMap<String, String>,
    countryCode: String
) {
    val namesToRemove = mutableListOf<String>()

    for ((name, phone) in phoneBook) {
        if (!phone.startsWith(countryCode)) {
            namesToRemove.add(name)
        }
    }

    for (name in namesToRemove) {
        phoneBook.remove(name)
    }
}

/**
 * Пример
 *
 * Для заданного текста `text` убрать заданные слова-паразиты `fillerWords`
 * и вернуть отфильтрованный текст
 */
fun removeFillerWords(
    text: List<String>,
    vararg fillerWords: String
): List<String> {
    val fillerWordSet = setOf(*fillerWords)

    val res = mutableListOf<String>()
    for (word in text) {
        if (word !in fillerWordSet) {
            res += word
        }
    }
    return res
}

/**
 * Пример
 *
 * Для заданного текста `text` построить множество встречающихся в нем слов
 */
fun buildWordSet(text: List<String>): MutableSet<String> {
    val res = mutableSetOf<String>()
    for (word in text) res.add(word)
    return res
}


/**
 * Простая
 *
 * По заданному ассоциативному массиву "студент"-"оценка за экзамен" построить
 * обратный массив "оценка за экзамен"-"список студентов с этой оценкой".
 *
 * Например:
 *   buildGrades(mapOf("Марат" to 3, "Семён" to 5, "Михаил" to 5))
 *     -> mapOf(5 to listOf("Семён", "Михаил"), 3 to listOf("Марат"))
 */
fun buildGrades(grades: Map<String, Int>): Map<Int, List<String>> {
    val result: MutableMap<Int, MutableList<String>> = mutableMapOf()
    for ((key, value) in grades) {
        if (result[value] != null) result[value]?.add(key)
        else result[value] = arrayListOf(key)
    }
    return result
}

/**
 * Простая
 *
 * Определить, входит ли ассоциативный массив a в ассоциативный массив b;
 * это выполняется, если все ключи из a содержатся в b с такими же значениями.
 *
 * Например:
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "z", "b" to "sweet")) -> true
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "zee", "b" to "sweet")) -> false
 */
fun containsIn(a: Map<String, String>, b: Map<String, String>): Boolean {
    a.forEach { (key, value) ->
        if (b.containsKey(key)) {
            if (b[key] != value) {
                return false
            }
        } else {
            return false
        }
    }
    return true
}

/**
 * Простая
 *
 * Удалить из изменяемого ассоциативного массива все записи,
 * которые встречаются в заданном ассоциативном массиве.
 * Записи считать одинаковыми, если и ключи, и значения совпадают.
 *
 * ВАЖНО: необходимо изменить переданный в качестве аргумента
 *        изменяемый ассоциативный массив
 *
 * Например:
 *   subtractOf(a = mutableMapOf("a" to "z"), mapOf("a" to "z"))
 *     -> a changes to mutableMapOf() aka becomes empty
 */
fun subtractOf(a: MutableMap<String, String>, b: Map<String, String>): MutableMap<String, String> {
    val tmpList: MutableList<String> = arrayListOf()
    a.forEach { (key, _) ->
        if (b.containsKey(key)) if (b[key] == a[key]) tmpList.add(key)
    }
    for (key in tmpList) a.remove(key)
    return a
}

/**
 * Простая
 *
 * Для двух списков людей найти людей, встречающихся в обоих списках.
 * В выходном списке не должно быть повторяюихся элементов,
 * т. е. whoAreInBoth(listOf("Марат", "Семён, "Марат"), listOf("Марат", "Марат")) == listOf("Марат")
 */
fun whoAreInBoth(a: List<String>, b: List<String>): List<String> {
    val resultList: MutableList<String> = arrayListOf()
    a.forEach { element ->
        if (element in b && element !in resultList) resultList.add(element)
    }
    return resultList
}

/**
 * Средняя
 *
 * Объединить два ассоциативных массива `mapA` и `mapB` с парами
 * "имя"-"номер телефона" в итоговый ассоциативный массив, склеивая
 * значения для повторяющихся ключей через запятую.
 * В случае повторяющихся *ключей* значение из mapA должно быть
 * перед значением из mapB.
 *
 * Повторяющиеся *значения* следует добавлять только один раз.
 *
 * Например:
 *   mergePhoneBooks(
 *     mapOf("Emergency" to "112", "Police" to "02"),
 *     mapOf("Emergency" to "911", "Police" to "02")
 *   ) -> mapOf("Emergency" to "112, 911", "Police" to "02")
 */
fun mergePhoneBooks(mapA: Map<String, String>, mapB: Map<String, String>): Map<String, String> {
    val mapResult: MutableMap<String, String> = mutableMapOf()
    mapA.forEach { (key, value) ->
        if (mapB.containsKey(key)) {
            if (mapA[key] == mapB[key]) mapResult[key] = value
            else mapResult[key] = value + ", " + mapB[key]
        } else mapResult[key] = value
    }
    mapB.forEach { (key, value) ->
        if (!mapResult.containsKey(key)) mapResult[key] = value
    }
    return mapResult
}

/**
 * Средняя
 *
 * Для заданного списка пар "акция"-"стоимость" вернуть ассоциативный массив,
 * содержащий для каждой акции ее усредненную стоимость.
 *
 * Например:
 *   averageStockPrice(listOf("MSFT" to 100.0, "MSFT" to 200.0, "NFLX" to 40.0))
 *     -> mapOf("MSFT" to 150.0, "NFLX" to 40.0)
 */
fun averageStockPrice(stockPrices: List<Pair<String, Double>>): Map<String, Double> {
    val tmpMap: MutableMap<String, MutableList<Double>> = mutableMapOf()
    val resultMap: MutableMap<String, Double> = mutableMapOf()
    stockPrices.forEach {
        if (tmpMap.containsKey(it.first)) tmpMap[it.first]?.add(it.second)
        else tmpMap[it.first] = mutableListOf(it.second)
    }
    tmpMap.forEach { (key, listOfPrice) ->
        resultMap[key] = listOfPrice.sum() / listOfPrice.size
    }
    return resultMap
}

/**
 * Средняя
 *
 * Входными данными является ассоциативный массив
 * "название товара"-"пара (тип товара, цена товара)"
 * и тип интересующего нас товара.
 * Необходимо вернуть название товара заданного типа с минимальной стоимостью
 * или null в случае, если товаров такого типа нет.
 *
 * Например:
 *   findCheapestStuff(
 *     mapOf("Мария" to ("печенье" to 20.0), "Орео" to ("печенье" to 100.0)),
 *     "печенье"
 *   ) -> "Мария"
 */
fun findCheapestStuff(stuff: Map<String, Pair<String, Double>>, kind: String): String? {
    var tmpMinPrice: Double? = null
    var result: String? = null
    stuff.forEach { (key, stuffPair) ->
        if (stuffPair.first == kind) {
            if (tmpMinPrice == null) {
                tmpMinPrice = stuffPair.second
                result = key
            } else if (tmpMinPrice!! > stuffPair.second) {
                tmpMinPrice = stuffPair.second
                result = key
            }
        }
    }
    return result
}

/**
 * Средняя
 *
 * Для заданного набора символов определить, можно ли составить из него
 * указанное слово (регистр символов игнорируется)
 *
 * Например:
 *   canBuildFrom(listOf('a', 'b', 'o'), "baobab") -> true
 */
fun canBuildFrom(chars: List<Char>, word: String): Boolean {
    for (i in word) if (i !in chars) return false
    return true
}

/**
 * Средняя
 *
 * Найти в заданном списке повторяющиеся элементы и вернуть
 * ассоциативный массив с информацией о числе повторений
 * для каждого повторяющегося элемента.
 * Если элемент встречается только один раз, включать его в результат
 * не следует.
 *
 * Например:
 *   extractRepeats(listOf("a", "b", "a")) -> mapOf("a" to 2)
 */
fun extractRepeats(list: List<String>): Map<String, Int> {
    val tmpRemoveList: MutableList<String> = mutableListOf()
    val resultMap: MutableMap<String, Int> = mutableMapOf()
    list.forEach { element ->
        if (resultMap.containsKey(element)) resultMap[element] = resultMap[element]!! + 1
        else resultMap[element] = 1
    }
    resultMap.forEach { (key, numbers) ->
        if (numbers < 2) tmpRemoveList.add(key)
    }
    for (key in tmpRemoveList) resultMap.remove(key)
    return resultMap
}

/**
 * Средняя
 *
 * Для заданного списка слов определить, содержит ли он анаграммы
 * (два слова являются анаграммами, если одно можно составить из второго)
 *
 * Например:
 *   hasAnagrams(listOf("тор", "свет", "рот")) -> true
 */
fun hasAnagrams(words: List<String>): Boolean {
    val tmpWordMap: MutableMap<String, MutableList<Char>> = mutableMapOf()
    words.forEach { word ->
        word.forEach {
            if (tmpWordMap.containsKey(word)) tmpWordMap[word]?.add(it)
            else tmpWordMap[word] = mutableListOf(it)
        }
    }
    for ((key1, charList1) in tmpWordMap) {
        for ((key2, charList2) in tmpWordMap) {
            if (key1 != key2 && charList1.sorted() == charList2.sorted()) return true
        }
    }
    return false
}

/**
 * Сложная
 *
 * Для заданного ассоциативного массива знакомых через одно рукопожатие `friends`
 * необходимо построить его максимальное расширение по рукопожатиям, то есть,
 * для каждого человека найти всех людей, с которыми он знаком через любое
 * количество рукопожатий.
 * Считать, что все имена людей являются уникальными, а также что рукопожатия
 * являются направленными, то есть, если Марат знает Свету, то это не означает,
 * что Света знает Марата.
 *
 * Например:
 *   propagateHandshakes(
 *     mapOf(
 *       "Marat" to setOf("Mikhail", "Sveta"),
 *       "Sveta" to setOf("Marat"),
 *       "Mikhail" to setOf("Sveta")
 *     )
 *   ) -> mapOf(
 *          "Marat" to setOf("Mikhail", "Sveta"),
 *          "Sveta" to setOf("Marat", "Mikhail"),
 *          "Mikhail" to setOf("Sveta", "Marat")
 *        )
 */

fun propagateHandshakes(friends: Map<String, Set<String>>): Map<String, Set<String>> {
    val tmpFriends: MutableMap<String, MutableSet<String>> = mutableMapOf()
    val result: MutableMap<String, MutableSet<String>> = mutableMapOf()
    friends.forEach { (key, value) ->
        tmpFriends[key] = value.toMutableSet()
    }
    tmpFriends.forEach { (name, friendList) ->
        friendList.forEach { friend ->
            if (tmpFriends[friend] != null) {
                tmpFriends[friend]?.forEach { itFriend ->
                    if (itFriend != name && !tmpFriends[name]!!.contains(itFriend)) {
                        tmpFriends[name]?.add(itFriend)
                    }
                }
            } else {
                tmpFriends[friend] = mutableSetOf()
            }
        }
    }
    tmpFriends.forEach { (name, value) ->
        result[name] = value.sorted().toMutableSet()
    }
    return result
}

/**
 * Сложная
 *
 * Для заданного списка неотрицательных чисел и числа определить,
 * есть ли в списке пара чисел таких, что их сумма равна заданному числу.
 * Если да, верните их индексы в виде Pair<Int, Int>;
 * если нет, верните пару Pair(-1, -1).
 *
 * Индексы в результате должны следовать в порядке (меньший, больший).
 *
 * Постарайтесь сделать ваше решение как можно более эффективным,
 * используя то, что вы узнали в данном уроке.
 *
 * Например:
 *   findSumOfTwo(listOf(1, 2, 3), 4) -> Pair(0, 2)
 *   findSumOfTwo(listOf(1, 2, 3), 6) -> Pair(-1, -1)
 */
fun findSumOfTwo(list: List<Int>, number: Int): Pair<Int, Int> {
    list.forEach { it1 ->
        list.forEach { it2 ->
            if (it1 != it2 && it1 + it2 == number) return Pair(list.indexOf(it1), list.indexOf(it2))
        }
    }
    return Pair(-1, -1)
}

/**
 * Очень сложная
 *
 * Входными данными является ассоциативный массив
 * "название сокровища"-"пара (вес сокровища, цена сокровища)"
 * и вместимость вашего рюкзака.
 * Необходимо вернуть множество сокровищ с максимальной суммарной стоимостью,
 * которые вы можете унести в рюкзаке.
 *
 * Перед решением этой задачи лучше прочитать статью Википедии "Динамическое программирование".
 *
 * Например:
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     850
 *   ) -> setOf("Кубок")
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     450
 *   ) -> emptySet()
 */
// Примечание - задача/тест является недоработанным. В перспективе алгоритм должен оценивать возможность
// максимального заполнения на основании вместимости и удельного коэф. стоимости (cofUnitCost)
fun bagPacking(treasures: Map<String, Pair<Int, Int>>, capacity: Int): Set<String> {
    val tmpTreasures: MutableMap<String, Pair<Int, Int>> = mutableMapOf()
    val cofUnitCost: MutableMap<String, Double> = mutableMapOf()
    treasures.forEach { (name, specs) ->
        if (specs.first < capacity) {
            tmpTreasures[name] = specs
            cofUnitCost[name] = specs.second.toDouble() / specs.first.toDouble()
        }
    }
    return if (tmpTreasures.isEmpty()) emptySet()
    else cofUnitCost.filterValues { it == cofUnitCost.values.sorted().reversed()[0] }.keys
}
