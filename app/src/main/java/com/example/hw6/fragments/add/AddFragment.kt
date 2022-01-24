package com.example.hw6.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hw6.R
import com.example.hw6.model.nodes.entities.AddNewNode
import com.example.hw6.model.nodes.room.entities.NodeDbEntity
import com.example.hw6.ui.main.NodeViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var mNodeViewModel: NodeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

//        mNodeViewModel = ViewModelProvider(this, NodeViewModelFactory()).get(NodeViewModel::class.java)
        mNodeViewModel = ViewModelProvider(this).get(NodeViewModel::class.java)
//        val myViewModel: MyViewModel = ViewModelProviders.of(
//            this,
//            MyViewModelFactory(this.getApplication(), "my awesome param")
//        ).get(
//            MyViewModel::class.java
//        )

        view.add_btn.setOnClickListener {
            insertDataToDatabase()
        }
        return view
    }

    private fun insertDataToDatabase() {
        val value = editValue.text.toString()

        if (inputCheck(value)) {
            val node = AddNewNode(value)
            mNodeViewModel.addNode(node)
            Toast.makeText(requireContext(), "Нода создана", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Укажите значение ноды", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(value: String): Boolean {
        return !(TextUtils.isEmpty(value))
    }
}
