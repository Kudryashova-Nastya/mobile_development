package com.example.hw6.fragments.relationship

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.hw6.R
import com.example.hw6.ui.main.NodeViewModel
import kotlinx.android.synthetic.main.fragment_relationship.*
import kotlinx.android.synthetic.main.fragment_relationship.view.*



class RelationshipFragment : Fragment() {

    private val args by navArgs<RelationshipFragmentArgs>()

    private lateinit var mNodeViewModel: NodeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_relationship, container, false)
    }


}

//class RelationFragment : Fragment() {
//
//    private val args by navArgs<RelationFragmentArgs>()
//
//    private lateinit var mNodeViewModel: NodeViewModel
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        // Inflate the layout for this fragment
//        val view = inflater.inflate(R.layout.fragment_relation, container, false)
//        val myElementValue = args.currentNode
//        val fragmentManager = (activity as FragmentActivity).supportFragmentManager
//
//        //RV recyclerView
//        view.childButton.setOnClickListener {
//            val adapter = RelationAdapter(myElementValue, true, fragmentManager)
//            val recyclerView = view.recycler_relation
//            recyclerView.adapter = adapter
//            recyclerView.layoutManager = LinearLayoutManager(requireContext())
//
//            //NodeViewModel
//            mNodeViewModel = ViewModelProvider(this)[NodeViewModel::class.java]
//            mNodeViewModel.readAlldata.observe(viewLifecycleOwner, Observer { node ->
//                adapter.setData(node)
//            })
//        }
//        //RV recyclerView
//        view.parentButton.setOnClickListener {
//            val adapter = RelationAdapter(myElementValue, false, fragmentManager)
//            val recyclerView = view.recycler_relation
//            recyclerView.adapter = adapter
//            recyclerView.layoutManager = LinearLayoutManager(requireContext())
//
//            //NodeViewModel
//            mNodeViewModel = ViewModelProvider(this)[NodeViewModel::class.java]
//            mNodeViewModel.readAlldata.observe(viewLifecycleOwner, Observer { node ->
//                adapter.setData(node)
//            })
//        }
//
//        // Inflate the layout for this fragment
//        return view
//    }
//
//}