package com.example.hw6.model.relationships.room.entities

import androidx.room.*
import com.example.hw6.model.nodes.room.entities.NodeDbEntity
import com.example.hw6.model.relationships.entities.*

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
    @ColumnInfo(name = "parent") val parent: Int,
    @ColumnInfo(name = "child") val child: Int
) {
    fun toRelationship(): Relationship = Relationship(
        id = id,
        parent = parent,
        child = child
    )

    companion object {
        fun fromAddNewRelationship(addNewRelationship: AddNewRelationship): RelationshipDbEntity =
            RelationshipDbEntity(
                id = 0,
                parent = addNewRelationship.parent,
                child = addNewRelationship.child,
            )
    }
}