package com.example.hw6.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hw6.model.nodes.room.NodesDao
import com.example.hw6.model.nodes.room.entities.NodeDbEntity
import com.example.hw6.model.relationships.room.RelationshipsDao
import com.example.hw6.model.relationships.room.entities.RelationshipDbEntity

//@Database(
//    version = 1,
//    entities = [NodeDbEntity::class, RelationshipDbEntity::class]
//)
//abstract class AppDatabase : RoomDatabase() {
//    abstract fun getNodesDao(): NodesDao
//    abstract fun getRelationshipsDao(): RelationshipsDao
//}

@Database(entities = [NodeDbEntity::class, RelationshipDbEntity::class], version = 1)
//@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {
    //    abstract fun dao(): Dao
    abstract fun getNodesDao(): NodesDao
    abstract fun getRelationshipsDao(): RelationshipsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}