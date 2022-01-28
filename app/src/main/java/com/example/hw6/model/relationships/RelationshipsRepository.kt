package com.example.hw6.model.relationships

import androidx.lifecycle.LiveData
import com.example.hw6.model.nodes.room.entities.NodeDbEntity
import com.example.hw6.model.relationships.entities.AddNewRelationship
import com.example.hw6.model.relationships.room.entities.RelationshipDbEntity
import kotlinx.coroutines.flow.Flow

interface RelationshipsRepository {

    suspend fun getAll(): LiveData<List<RelationshipDbEntity?>>

    suspend fun getParents(relationshipId: Long): LiveData<List<RelationshipDbEntity?>>

    suspend fun getChildren(relationshipId: Long): LiveData<List<RelationshipDbEntity?>>

    suspend fun deleteRelationship(parentId: Long, childId: Long)

    suspend fun createRelationship(addNewRelationship: AddNewRelationship)
}