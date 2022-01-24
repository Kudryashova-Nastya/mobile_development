package com.example.hw6.model.nodes

import androidx.lifecycle.LiveData
import com.example.hw6.model.nodes.entities.AddNewNode
import kotlinx.coroutines.flow.Flow
import com.example.hw6.model.nodes.room.entities.NodeDbEntity

interface NodesRepository {

    suspend fun getById(nodeId: Long): LiveData<NodeDbEntity?>

    suspend fun getAll(): LiveData<List<NodeDbEntity?>>

    suspend fun createNode(addNewNode: AddNewNode)

}