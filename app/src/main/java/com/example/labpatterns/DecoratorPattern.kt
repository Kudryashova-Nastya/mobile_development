package com.example.labpatterns

//Декоратор— это структурный паттерн проектирования, который позволяет динамически добавлять
// объектам новую функциональность, оборачивая их в полезные «обёртки».

// Рассмотрим на примере обедов в столовой
interface Lunch {
    fun addSoup()
    fun addSandwich()
    fun addCompote()
}

// Стандартный обед для бедных студентов выглядит так
open class BaseLunch: Lunch {
    override fun addSoup() {
        print("Ваш супчик уже разогревается")
    }

    override fun addSandwich() {
        print("Бутербродик с колбаской и сыром ммм")
    }

    override fun addCompote() {
        print("Если повезёт компот ягодный, а не из сухофруктов")
    }
}

//Лакшери обед состоит из того же что и стандартный плюс вкусняшка для богатых
class VIPLunch(val lunch: Lunch): BaseLunch by baseLunch {
    fun addBurger() {
        baseLunch.addSandwich()
        print("Окей, лови котлетку сверху")
    }

    fun addCoffee() {
        print("Лишним не будет, проснись уже")
    }

    fun addCake() {
        print("Кусочек небольшой, иногда можно себя побаловать")
    }
}