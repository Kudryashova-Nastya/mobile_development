package com.example.hw6.model.nodes.room.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.hw6.model.nodes.entities.AddNewNode
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    tableName = "Node",
    indices = [Index("value", unique = true)]
)
data class NodeDbEntity (
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "value") val value: String
) : Parcelable {

    companion object {
        fun fromAddNewNode(addNewNode: AddNewNode): NodeDbEntity = NodeDbEntity(
            id = 0,
            value = addNewNode.value
        )
    }
}
