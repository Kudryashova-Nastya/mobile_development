package com.example.hw6.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hw6.model.nodes.room.NodesDao
import com.example.hw6.model.nodes.room.entities.NodeDbEntity

@Database(version = 1, entities = [NodeDbEntity::class])
abstract class AppDatabase: RoomDatabase() {
    abstract fun getNodesDao(): NodesDao
}