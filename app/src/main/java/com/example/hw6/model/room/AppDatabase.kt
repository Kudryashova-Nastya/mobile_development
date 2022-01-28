package com.example.hw6.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hw6.model.nodes.room.NodesDao
import com.example.hw6.model.nodes.room.entities.NodeDbEntity
import com.example.hw6.model.relationships.room.RelationshipsDao
import com.example.hw6.model.relationships.room.entities.RelationshipDbEntity

@Database(entities = [NodeDbEntity::class, RelationshipDbEntity::class], version = 4)
//@Database(entities = [NodeDbEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getNodesDao(): NodesDao
    abstract fun getRelationshipsDao(): RelationshipsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}