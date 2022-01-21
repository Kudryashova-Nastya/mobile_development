package com.example.hw6.model.relationships.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.hw6.model.nodes.room.entities.NodeDbEntity
import kotlinx.coroutines.flow.Flow
import com.example.hw6.model.relationships.room.entities.*

@Dao
interface RelationshipsDao {

    @Insert(entity = RelationshipDbEntity::class)
    suspend fun createRelationship(RelationshipDbEntity: RelationshipDbEntity)

    @Query("SELECT * FROM Relationship WHERE child = :nodeId")
    fun getParentsById(nodeId: Long): Flow<List<RelationshipDbEntity?>?>

    @Query("SELECT * FROM Relationship WHERE parent = :nodeId")
    fun getChildrenById(nodeId: Long): Flow<List<RelationshipDbEntity?>?>

    @Query("DELETE FROM Relationship WHERE id = :relationshipId")
    fun deleteById(relationshipId: Long)
}