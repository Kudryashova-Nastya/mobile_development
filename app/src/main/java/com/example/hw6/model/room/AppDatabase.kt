package com.example.hw6.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hw6.model.nodes.room.NodesDao
import com.example.hw6.model.nodes.room.entities.NodeDbEntity
import com.example.hw6.model.relationships.room.RelationshipsDao
import com.example.hw6.model.relationships.room.entities.RelationshipDbEntity

@Database(
    version = 1,
    entities = [NodeDbEntity::class, RelationshipDbEntity::class]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getNodesDao(): NodesDao
    abstract fun getRelationshipsDao(): RelationshipsDao
}