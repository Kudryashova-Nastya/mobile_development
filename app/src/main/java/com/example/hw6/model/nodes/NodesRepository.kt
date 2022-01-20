package com.example.hw6.model.nodes

import com.example.hw6.model.nodes.entities.AddNewNode
import kotlinx.coroutines.flow.Flow
import com.example.hw6.model.nodes.entities.Node

interface NodesRepository {

    suspend fun getById(): Flow<Node?>

    suspend fun updateNodeColor(newColor: String)

    suspend fun createNode(addNewNode: AddNewNode)

}