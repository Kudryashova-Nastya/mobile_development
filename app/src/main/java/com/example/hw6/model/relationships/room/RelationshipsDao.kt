package com.example.hw6.model.relationships.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import com.example.hw6.model.relationships.room.entities.*

@Dao
interface RelationshipsDao {

    @Insert(entity = RelationshipDbEntity::class)
    suspend fun createRelationship(RelationshipDbEntity: RelationshipDbEntity)

    @Query("SELECT * FROM Relationship WHERE child = :nodeId")
    fun getParentsById(nodeId: Long): LiveData<List<RelationshipDbEntity?>>

    @Query("SELECT * FROM Relationship WHERE parent = :nodeId")
    fun getChildrenById(nodeId: Long): LiveData<List<RelationshipDbEntity?>>

    @Query("DELETE FROM Relationship WHERE id = :relationshipId")
    fun deleteById(relationshipId: Long)
}