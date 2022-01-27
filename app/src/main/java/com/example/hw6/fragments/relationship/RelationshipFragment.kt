package com.example.hw6.fragments.relationship

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw6.R
import com.example.hw6.model.relationships.room.entities.RelationshipDbEntity
import com.example.hw6.ui.main.NodeViewModel
import com.example.hw6.ui.main.RelationshipViewModel
import kotlinx.android.synthetic.main.fragment_relationship.view.*


class RelationshipFragment : Fragment() {

    private val args by navArgs<RelationshipFragmentArgs>()

    private lateinit var mNodeViewModel: NodeViewModel
    private lateinit var mRelationshipViewModel: RelationshipViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_relationship, container, false)
        val myElementValue = args.currentNode
        val fragmentManager = (activity as FragmentActivity).supportFragmentManager


        var adapter = RelationshipAdapter(myElementValue, true, fragmentManager)
        val recyclerView = view.relationships_container
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mNodeViewModel = ViewModelProvider(this)[NodeViewModel::class.java]
        mNodeViewModel.readAllData.observe(viewLifecycleOwner, { node ->
            adapter.setData(node)
        })

        mRelationshipViewModel = ViewModelProvider(this)[RelationshipViewModel::class.java]
        mRelationshipViewModel.readAllData.observe(
            viewLifecycleOwner,
            { relationship ->
                adapter.setRelationship(relationship as List<RelationshipDbEntity>)
            }
        )

        view.btn_children.setBackgroundColor(Color.LTGRAY)


        view.btn_children.setOnClickListener {
            adapter = RelationshipAdapter(myElementValue, true, fragmentManager)
            recyclerView.adapter = adapter //

            mNodeViewModel.readAllData.observe(viewLifecycleOwner, { node ->
                adapter.setData(node)
            })
            mRelationshipViewModel.readAllData.observe(
                viewLifecycleOwner,
                { relationship ->
                    adapter.setRelationship(relationship as List<RelationshipDbEntity>)
                }
            )
            view.btn_children.setBackgroundColor(Color.LTGRAY)
            view.btn_parents.setBackgroundColor(Color.parseColor("#FF6200EE"))
        }

        view.btn_parents.setOnClickListener {
            adapter = RelationshipAdapter(myElementValue, false, fragmentManager)
            recyclerView.adapter = adapter //

            mNodeViewModel.readAllData.observe(viewLifecycleOwner, { node ->
                adapter.setData(node)
            })
            mRelationshipViewModel.readAllData.observe(
                viewLifecycleOwner,
                { relationship ->
                    adapter.setRelationship(relationship as List<RelationshipDbEntity>)
                }
            )

            view.btn_parents.setBackgroundColor(Color.LTGRAY)
            view.btn_children.setBackgroundColor(Color.parseColor("#FF6200EE"))


        }
        return view
    }
}