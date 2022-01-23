package com.example.hw6;

import android.app.Application
import androidx.room.Room

import com.example.hw6.model.room.AppDatabase

class App : Application() {
    var database: AppDatabase? = null
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, AppDatabase::class.java, "database")
            .build()
    }

    companion object {
        var instance: App? = null
    }
}
// всё ещё не работает(((((((((((