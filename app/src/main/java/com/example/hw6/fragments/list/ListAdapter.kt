package com.example.hw6.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.hw6.R
import kotlinx.android.synthetic.main.node.view.*
import com.example.hw6.model.nodes.room.entities.NodeDbEntity

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var nodeList = emptyList<NodeDbEntity>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.node, parent, false))
    }

    override fun getItemCount(): Int {
        return nodeList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = nodeList[position]
        holder.itemView.textNode.text = "id: " + currentItem.id.toString() + " | value: " + currentItem.value.toString()

//        holder.itemView.rowLayout.setOnClickListener {
//            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
//            holder.itemView.findNavController().navigate(action)
//        }
    }

    fun setData(node: List<NodeDbEntity>) {
        this.nodeList = node
        notifyDataSetChanged()
    }
}
