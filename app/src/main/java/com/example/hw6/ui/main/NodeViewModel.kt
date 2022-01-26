package com.example.hw6.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.hw6.model.nodes.entities.AddNewNode
import com.example.hw6.model.nodes.room.RoomNodesRepository
import com.example.hw6.model.nodes.room.entities.NodeDbEntity
import com.example.hw6.model.room.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NodeViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<NodeDbEntity?>>
    private val repository: RoomNodesRepository

    init {
        val nodesDAO = AppDatabase.getDatabase(application).getNodesDao()
        repository = RoomNodesRepository(nodesDAO)
        readAllData = nodesDAO.getAll()
//        readAllData = repository.readAllData
    }


    fun addNode(node: AddNewNode) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.createNode(node)
        }
    }
}