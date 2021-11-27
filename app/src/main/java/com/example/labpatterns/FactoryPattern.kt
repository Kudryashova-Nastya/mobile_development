package com.example.labpatterns

//Абстрактная фабрика— это порождающий паттерн проектирования, который позволяет создавать семейства
// связанных объектов, не привязываясь к конкретным классам создаваемых объектов.


// Есть несколько семейств объектов, находящихся в отдельных иерархиях классов Button и Checkbox.

interface Button {
    fun paint()
}

interface Checkbox {
    fun paint()
}

//Абстрактная фабрика знает обо всех (абстрактных) типах продуктов.

interface AbsFactory {
    fun createButton(): Button?
    fun createCheckbox(): Checkbox?
}

//Каждая конкретная фабрика знает и создаёт только продукты своей вариации.

class Factory(factory: AbsFactory) {
    private val button: Button?
    private val checkbox: Checkbox?

    init {
        button = factory.createButton()
        checkbox = factory.createCheckbox()
    }

}
