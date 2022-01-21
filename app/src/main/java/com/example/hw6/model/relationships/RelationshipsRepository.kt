package com.example.hw6.model.relationships

import com.example.hw6.model.nodes.entities.AddNewNode
import com.example.hw6.model.relationships.entities.AddNewRelationship
import com.example.hw6.model.relationships.entities.Relationship
import kotlinx.coroutines.flow.Flow

interface RelationshipsRepository {

    suspend fun getParents(relationshipId: Long): Flow<List<Relationship?>?>

    suspend fun getChildren(relationshipId: Long): Flow<List<Relationship?>?>

    suspend fun deleteRelationship(relationshipId: Long)

    suspend fun createRelationship(addNewRelationship: AddNewRelationship)
}