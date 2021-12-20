package com.example.hw5

import androidx.annotation.DrawableRes
import java.util.*

object Holder {

    private val persons = mutableListOf<Person>()


    fun createCollection(mes: String): MutableList<Person> {

        val person = Person(
            mes
        )
        persons.add(person)
        return persons
    }
}


data class Person(
    val text: String
)