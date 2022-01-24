package com.example.hw6.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.hw6.model.nodes.entities.Node
import com.example.hw6.model.room.AppDatabase

class NodeViewModel(application: Application) : AndroidViewModel(application) {
//    val readAllData: LiveData<List<Node>>
//    private val repository: NodeRepository

    init {
        val nodeDAO = AppDatabase.getDatabase(application).getNodesDao()
//        repository = NodeRepository(nodeDAO)
//        readAllData = repository.readAllData
    }

//    fun addNode(node: Node){
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.addNode(node)
//        }
//    }
}