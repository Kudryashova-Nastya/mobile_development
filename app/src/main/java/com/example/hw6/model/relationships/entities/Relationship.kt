package com.example.hw6.model.relationships.entities

data class Relationship(
    val id: Long,
    val parent: Int,
    val child: Int
)