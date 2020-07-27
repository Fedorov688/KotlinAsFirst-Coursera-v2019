@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result *= i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var tmp: Int = n
    var numbers: Int = 0
    do {
        tmp /= 10
        numbers += 1
    } while (tmp != 0)
    return numbers
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var n1: Int = 1
    var n2: Int = 1
    var result: Int = 1
    if (n == 1 || n == 2) return result
    else {
        for (i in 3..n) {
            result = n1 + n2
            n1 = n2
            n2 = result
        }
    }
    return result
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
// 19.793s not optimal algorithm, but works
    var k: Int
    if (m > n) k = m
    else if (m < n) k = n
    else return m
    while ((k % m != 0) || (k % n != 0)) {
        k++
    }
    return k
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var k: Int = 2
    while (n % k != 0) k++
    return k
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var k: Int = n - 1
    while (n % k != 0) k--
    return k
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
// 7.128s 13.762s с добавленным тестом
//    if (m < n) {
//        for (i in 2..m) {
//            if (m % i == 0 && n % i == 0) return false
//        }
//        return true
//    } else if (m > n) {
//        for (i in 2..n) {
//            if (m % i == 0 && n % i == 0) return false
//        }
//        return true
//    } else return false
//
// 6.491s 13.20s с добавленным тестом
    val mnList: MutableList<Int> = arrayListOf()
    if (m < n) {
        for (i in 2..m) {
            if (m % i == 0) {
                mnList.add(i)
            }
        }
        for (element in mnList) {
            if (n % element == 0) return false
        }
    } else if (m > n) {
        for (i in 2..n) {
            if (n % i == 0) {
                mnList.add(i)
            }
        }
        for (element in mnList) {
            if (m % element == 0) return false
        }
    }
    return true
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    var k: Long = 1
    val sqrtN = sqrt(n.toDouble()).toInt() + 1
    while (k * k !in m..n && k <= sqrtN) k++
    return k * k in m..n
}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var tmpX: Int = x
    var i: Int = 0
    while (tmpX != 1) {
        if (tmpX % 2 == 0) tmpX /= 2
        else tmpX = 3 * tmpX + 1
        i++
    }
    return i
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun factorialCustom(n: Int): Long {
    var result: Long = 1
    for (i in 1..n) result *= i
    return result
}

fun sin(x: Double, eps: Double): Double {
    var n: Int = 3
    var checker: Double
    var checkerFactCus: Long
    var checkerPow: Double
    var symbol: Int
    val xr: Double = if (x > 4 * PI) x / PI % 4 * PI else x
    var sinX: Double = xr
    do {
        symbol = if ((n - 3) / 2 % 2 == 0) -1 else 1
        checkerPow = xr.pow(n)
        checkerFactCus = factorialCustom(n)
        checker = checkerPow / checkerFactCus
        sinX += (symbol * checker)
        if (checker < 0) checker = -checker
        n += 2
    } while (checker > eps)
    return sinX
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double {
    var n: Int = 2
    var checker: Double
    var checkerFactCus: Long
    var checkerPow: Double
    var symbol: Int
    val xr: Double = if (x > 4 * PI) x / PI % 4 * PI else x
    var cosX: Double = 1.0
    do {
        symbol = if ((n - 2) / 2 % 2 == 0) -1 else 1
        checkerPow = xr.pow(n)
        checkerFactCus = factorialCustom(n)
        checker = checkerPow / checkerFactCus
        cosX += (symbol * checker)
        if (checker < 0) checker = -checker
        n += 2
    } while (checker > eps)
    return cosX
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    val decimal: Double = 10.0
    val listOfNumbers: MutableList<Int> = arrayListOf()
    var number: Int
    var level: Int = 0
    var result: Int = 0
    while (n / decimal.pow(level).toInt() > 0) {
        number = (n % decimal.pow(level + 1) / decimal.pow(level)).toInt()
        listOfNumbers.add(number)
        level++
    }
    for (i in listOfNumbers) {
        result += i * decimal.pow(level - 1).toInt()
        level--
    }
    return result
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    val decimal: Double = 10.0
    val listOfNumbers: MutableList<Int> = arrayListOf()
    var number: Int
    var level: Int = 0
    while (n / decimal.pow(level).toInt() > 0) {
        number = (n % decimal.pow(level + 1) / decimal.pow(level)).toInt()
        listOfNumbers.add(number)
        level++
    }
    return listOfNumbers == listOfNumbers.reversed()
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    val decimal: Double = 10.0
    val checkNumber: Int = (n % decimal.toInt())
    var number: Int
    var level: Int = 0
    while (n / decimal.pow(level).toInt() > 0) {
        number = (n % decimal.pow(level + 1) / decimal.pow(level)).toInt()
        if (checkNumber != number) return true
        level++
    }
    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var targetLen: Int = n
    var sqrNumber: Int = 1
    var tmpNumber: Int = 1
    var i: Int
    val result: Int
    while (targetLen > 0) {
        sqrNumber = tmpNumber * tmpNumber
        i = 0
        while (sqrNumber / 10.0.pow(i).toInt() > 0) {
            i++
            targetLen--
        }
        tmpNumber++
    }
    result = if (n > 0) {
        sqrNumber % 10.0.pow(abs(targetLen) + 1).toInt() / 10.0.pow(abs(targetLen)).toInt()
    } else 0
    return result
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int = TODO()
