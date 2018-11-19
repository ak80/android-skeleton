package org.ak80.skeleton

import org.ak80.skeleton.Counter.count
import org.ak80.skeleton.model.Entry

private object Counter {

    var count: Int = 1

}

fun anInt() = count++

fun aTitle() = "Title${anInt()}"

fun anEntry(title: String = aTitle(), passed: Boolean = false): Entry = Entry(title, passed)