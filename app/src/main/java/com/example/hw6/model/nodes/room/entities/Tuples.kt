package com.example.hw6.model.nodes.room.entities

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class NodeUpdateColorTuple(
    @ColumnInfo(name = "id") @PrimaryKey val id: Long,
    @ColumnInfo(name = "color") val color: String
)
