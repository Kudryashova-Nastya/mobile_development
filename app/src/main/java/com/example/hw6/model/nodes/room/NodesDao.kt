package com.example.hw6.model.nodes.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import com.example.hw6.model.nodes.room.entities.*

@Dao
interface NodesDao {

    @Query("SELECT * FROM Node")
    fun getAll(): Flow<List<NodeDbEntity?>>

    @Insert(entity = NodeDbEntity::class)
    suspend fun createNode(NodeDbEntity: NodeDbEntity)

    @Query("SELECT * FROM Node WHERE id = :nodeId")
    fun getById(nodeId: Long): Flow<NodeDbEntity?>
}