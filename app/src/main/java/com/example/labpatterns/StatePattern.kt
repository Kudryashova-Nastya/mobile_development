package com.example.labpatterns

//Состояние— это поведенческий паттерн проектирования, который позволяет объектам менять поведение
// в зависимости от своего состояния. Извне создаётся впечатление, что изменился класс объекта.

// В классе LightState указаны все возможные состояния лампочки в условном умном доме
sealed class LightState {
    object LightOff: LightState()
    object LightOn: LightState()
}

// В классе Light происходит изменение состояния освещения с вкл на выкл и наоборот
class Light {
    private var state: LightState = LightState.LightOff

    val isLight: Boolean
    get() = when (state) {
        is LightState.LightOn -> true
        is LightState.LightOff -> false
    }

    fun turnOn() {
        state = LightState.LightOn
    }

    fun turnOff() {
        state = LightState.LightOff
    }

    // Сообщение о состоянии освещения
    override fun toString() = "at the moment the light in the room is $isLight"
}

// Вот так, например, можно включить свет и вывести сообщение о его состоянии на данный момент
val light = Light()
light.turnOn()
println(light)

