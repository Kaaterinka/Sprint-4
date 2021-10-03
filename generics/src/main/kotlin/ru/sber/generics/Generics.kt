package ru.sber.generics

import java.util.*

// 1.
fun <T1, T2> compare(p1: Pair<T1, T2>, p2: Pair<T1, T2>): Boolean {
    return p1 == p2
}

//// 2.
fun <T:Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T): Int {
    var count = 0
    for (element in anArray) {
        if (element > elem)
            count++
    }
    return count
}

//// 3.
class Sorter<T : Comparable<T>> {
    val list: MutableList<T> = mutableListOf()

    fun add(value: T) {
        list.add(value)
        var tmp: T
        for (i in list.lastIndex downTo 1) {
            if (list[i] < list[i - 1]) {
                tmp = list[i - 1]
                list[i - 1] = list[i]
                list[i] = tmp
            }
        }
    }
}


//// 4.
class Stack<T> {
    val stack = mutableListOf<T>()
    fun isEmpty(): Boolean {
        return stack.isEmpty()
    }

    fun push(value: T) {
        stack.add(value)
    }

    fun pop(): T {
        val value = stack.last()
        if (!isEmpty())
            stack.removeLast()
        return value
    }
}
