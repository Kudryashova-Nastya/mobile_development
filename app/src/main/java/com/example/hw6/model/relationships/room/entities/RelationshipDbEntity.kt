package com.example.hw6.model.relationships.room.entities

import android.os.Parcelable
import androidx.room.*
import com.example.hw6.model.nodes.room.entities.NodeDbEntity
import com.example.hw6.model.relationships.entities.*
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    tableName = "Relationship",
    foreignKeys = [
        ForeignKey(
            entity = NodeDbEntity::class,
            parentColumns = ["id"],
            childColumns = ["parent"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = NodeDbEntity::class,
            parentColumns = ["id"],
            childColumns = ["child"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class RelationshipDbEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "parent") val parent: Long,
    @ColumnInfo(name = "child") val child: Long
) : Parcelable {

    companion object {
        fun fromAddNewRelationship(relationship: AddNewRelationship): RelationshipDbEntity =
            RelationshipDbEntity(
                id = 0,
                parent = relationship.parent,
                child = relationship.child,
            )
    }
}