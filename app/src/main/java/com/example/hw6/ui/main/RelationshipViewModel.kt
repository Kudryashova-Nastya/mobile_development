package com.example.hw6.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.hw6.model.relationships.entities.AddNewRelationship
import com.example.hw6.model.relationships.room.RelationshipsDao
import com.example.hw6.model.relationships.room.RoomRelationshipsRepository
import com.example.hw6.model.relationships.room.entities.RelationshipDbEntity
import com.example.hw6.model.room.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RelationshipViewModel(application: Application) : AndroidViewModel(application) {
    //    val readParentsData: LiveData<List<RelationshipDbEntity?>>
    private val repository: RoomRelationshipsRepository
    private val relationshipsDAO: RelationshipsDao =
        AppDatabase.getDatabase(application).getRelationshipsDao()

    init {
        repository = RoomRelationshipsRepository(relationshipsDAO)
//        readParentsData = repository.getParents(1)
    }

    fun readParents(relationshipId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val readParentsData = relationshipsDAO.getParentsById(relationshipId)
            readParentsData
        }
    }


    fun addRelationship(relationship: AddNewRelationship) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.createRelationship(relationship)
        }
    }
}