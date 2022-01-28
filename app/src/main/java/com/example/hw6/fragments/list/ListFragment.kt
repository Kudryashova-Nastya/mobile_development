package com.example.hw6.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw6.R
import com.example.hw6.model.nodes.room.entities.NodeDbEntity
import com.example.hw6.model.relationships.room.entities.RelationshipDbEntity
import com.example.hw6.ui.main.NodeViewModel
import com.example.hw6.ui.main.RelationshipViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

    private lateinit var mNodeViewModel: NodeViewModel
    private lateinit var mRelationshipViewModel: RelationshipViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        mRelationshipViewModel = ViewModelProvider(this)[RelationshipViewModel::class.java]

        val adapter = ListAdapter()


        val recyclerView = view.recycleview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mNodeViewModel = ViewModelProvider(this)[NodeViewModel::class.java]

        mNodeViewModel.readAllData.observe(
            viewLifecycleOwner,
            { node ->
                adapter.setData(node as List<NodeDbEntity>)
            }
        )

        mRelationshipViewModel.readAllData.observe(
            viewLifecycleOwner,
            { relationship ->
                adapter.setRelationship(relationship as List<RelationshipDbEntity>)
            }
        )

        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        return view
    }
}
