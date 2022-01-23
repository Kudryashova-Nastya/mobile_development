package com.example.hw6

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import com.example.hw6.model.nodes.NodesRepository
import com.example.hw6.model.nodes.room.RoomNodesRepository
import com.example.hw6.model.relationships.RelationshipsRepository
import com.example.hw6.model.relationships.room.RoomRelationshipsRepository
import com.example.hw6.model.room.AppDatabase

import android.app.Application

//import com.example.hw6.model.settings.AppSettings
//import com.example.hw6.model.settings.SharedPreferencesAppSettings

// не работает(((((((((((
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




//object Repositories {
//
//    private lateinit var applicationContext: Context
//
//    // -- stuffs
//
//    private val database: AppDatabase by lazy<AppDatabase> {
//        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database.db")
//            .build()
//    }
//
//    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
//
//    private val appSettings: AppSettings by lazy {
//        SharedPreferencesAppSettings(applicationContext)
//    }
//
//    // --- repositories
//
//    val nodesRepository: NodesRepository by lazy {
//        RoomNodesRepository(database.getNodesDao(), appSettings, ioDispatcher)
////        RoomNodesRepository(database.getNodesDao())
//    }
//
//    val relationshipsRepository: RelationshipsRepository by lazy {
//        RoomRelationshipsRepository(nodesRepository, database.getRelationshipsDao(), ioDispatcher)
////        RoomRelationshipsRepository(database.getRelationshipsDao())
//    }
//
//    /**
//     * Call this method in all application components that may be created at app startup/restoring
//     * (e.g. in onCreate of activities and services)
//     */
//    fun init(context: Context) {
//        applicationContext = context
//    }
//
//
//}