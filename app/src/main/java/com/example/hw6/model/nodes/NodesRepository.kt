package com.example.hw6.model.nodes

import com.example.hw6.model.nodes.entities.AddNewNode
import kotlinx.coroutines.flow.Flow
import com.example.hw6.model.nodes.entities.Node

interface NodesRepository {

    suspend fun getById(nodeId: Long): Flow<Node?>

    suspend fun getAll(): Flow<List<Node?>>

    suspend fun createNode(addNewNode: AddNewNode)

}