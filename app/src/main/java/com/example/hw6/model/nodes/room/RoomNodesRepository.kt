package com.example.hw6.model.nodes.room

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.LiveData
import com.example.hw6.model.nodes.NodesRepository
import com.example.hw6.model.nodes.entities.AddNewNode
import com.example.hw6.model.nodes.room.entities.NodeDbEntity

class RoomNodesRepository(
    private val nodesDao: NodesDao
) : NodesRepository {

    override suspend fun getById(nodeId: Long): LiveData<NodeDbEntity?> {
        return nodesDao.getById(nodeId)
    }

    override suspend fun getAll(): LiveData<List<NodeDbEntity?>> {
        return nodesDao.getAll()
    }

    val readAllData: LiveData<List<NodeDbEntity?>> = nodesDao.getAll()


    override suspend fun createNode(addNewNode: AddNewNode) {
        try {
            val entity = NodeDbEntity.fromAddNewNode(addNewNode)
            nodesDao.createNode(entity)
        } catch (e: SQLiteConstraintException) {
            val appException = RuntimeException()
            appException.initCause(e)
            throw appException
        }
    }

}