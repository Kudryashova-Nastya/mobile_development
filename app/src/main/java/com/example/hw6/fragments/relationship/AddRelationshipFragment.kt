package com.example.hw6.fragments.relationship

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.hw6.R
import com.example.hw6.model.nodes.room.entities.NodeDbEntity
import com.example.hw6.model.relationships.entities.AddNewRelationship
import com.example.hw6.ui.main.RelationshipViewModel
import kotlinx.android.synthetic.main.add_relationship.view.*

class AddRelationshipFragment(
    private var parentNode: NodeDbEntity,
    private var childNode: NodeDbEntity
) : DialogFragment() {
    private lateinit var mRelationshipViewModel: RelationshipViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.add_relationship, container, false)

        mRelationshipViewModel = ViewModelProvider(this)[RelationshipViewModel::class.java]

        view.ok_relation_button.setOnClickListener {
            insertDataToDatabase()
            dismiss()
        }
        view.cancel_relation_button.setOnClickListener {
            dismiss()
        }
        return view
    }

    private fun insertDataToDatabase() {
        val relationship = AddNewRelationship(parentNode.id, childNode.id)
        mRelationshipViewModel.addRelationship(relationship)
    }
}