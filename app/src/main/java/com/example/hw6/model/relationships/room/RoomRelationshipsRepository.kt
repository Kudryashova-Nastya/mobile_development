package com.example.hw6.model.relationships.room

import android.database.sqlite.SQLiteConstraintException
import com.example.hw6.model.relationships.RelationshipsRepository
import com.example.hw6.model.relationships.entities.AddNewRelationship
import com.example.hw6.model.relationships.entities.Relationship
import com.example.hw6.model.relationships.room.entities.RelationshipDbEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomRelationshipsRepository(
    private val relationshipsDao: RelationshipsDao,
) : RelationshipsRepository {
    override suspend fun getParents(relationshipId: Long): Flow<List<Relationship?>?> {
        return relationshipsDao.getParentsById(relationshipId)
            .map { relationship ->
                relationship?.map { relationshipDbEntity -> relationshipDbEntity?.toRelationship() }
            }
    }

    override suspend fun getChildren(relationshipId: Long): Flow<List<Relationship?>?> {
        return relationshipsDao.getChildrenById(relationshipId)
            .map { relationship ->
                relationship?.map { relationshipDbEntity -> relationshipDbEntity?.toRelationship() }
            }
    }

    override suspend fun deleteRelationship(relationshipId: Long) {
        relationshipsDao.deleteById(relationshipId)
    }

    override suspend fun createRelationship(addNewRelationship: AddNewRelationship) {
        try{
            val entity = RelationshipDbEntity.fromAddNewRelationship(addNewRelationship)
            relationshipsDao.createRelationship(entity)
        } catch(e: SQLiteConstraintException) {
            val appException = RuntimeException()
            appException.initCause(e)
            throw appException
        }
    }
}