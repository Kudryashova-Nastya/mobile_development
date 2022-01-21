package com.example.hw6.model.nodes.room

import android.database.sqlite.SQLiteConstraintException
import com.example.hw6.model.nodes.NodesRepository
import com.example.hw6.model.nodes.entities.AddNewNode
import com.example.hw6.model.nodes.entities.Node
import com.example.hw6.model.nodes.room.entities.NodeDbEntity
import com.example.hw6.model.nodes.room.entities.NodeUpdateColorTuple
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomNodesRepository(
    private val nodesDao: NodesDao,
//    private val appSettings: AppSettings,
//    private val ioDispatcher: CoroutineDispatcher
) : NodesRepository {

    override suspend fun getById(nodeId: Long): Flow<Node?> {
        return nodesDao.getById(nodeId).map { nodeDbEntity -> nodeDbEntity?.toNode() }
    }

    override suspend fun updateNodeColor(nodeId: Long, newColor: String) {
        nodesDao.updateColor(NodeUpdateColorTuple(nodeId, newColor))
    }

    override suspend fun createNode(addNewNode: AddNewNode) {
        try{
            val entity = NodeDbEntity.fromAddNewNode(addNewNode)
            nodesDao.createNode(entity)
        } catch(e: SQLiteConstraintException) {
            val appException = RuntimeException()
            appException.initCause(e)
            throw appException
        }
    }

}