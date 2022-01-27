package com.example.hw6.fragments.list

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.hw6.R
import kotlinx.android.synthetic.main.node.view.*
import com.example.hw6.model.nodes.room.entities.NodeDbEntity
import com.example.hw6.model.relationships.room.entities.RelationshipDbEntity

class ListAdapter() :
    RecyclerView.Adapter<ListAdapter.MyViewHolder>() {


    private var nodeList = emptyList<NodeDbEntity>()
    private var relationshipList = emptyList<RelationshipDbEntity>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.node, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return nodeList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d("relationshipList", relationshipList.toString())
        val currentItem = nodeList[position]
        var hasParents = false
        var hasChildren = false

        holder.itemView.textNode.text =
            "id: " + currentItem.id.toString() + " | value: " + currentItem.value.toString()

//        holder.itemView.textNode.text =
//            "id: " + currentItem.id.toString() + " | relationship: " + nodeList.toString()

        if (relationshipList.isNullOrEmpty().not()) {
            if (currentItem.id in relationshipList.map { it -> it.child }) {
                hasParents = true
            }
        }

        if (relationshipList.isNullOrEmpty().not()) {
            if (currentItem.id in relationshipList.map { it -> it.parent }) {
                hasChildren = true
            }
        }

        if (hasParents) {
            if (hasChildren) {
                holder.itemView.node.setBackgroundColor(Color.parseColor("#F5788D"))
            } else {
                holder.itemView.node.setBackgroundColor(Color.parseColor("#B0C1F5"))
            }
        } else if (hasChildren) {
            holder.itemView.node.setBackgroundColor(Color.parseColor("#FFFF6D"))
        }

        holder.itemView.rowLayout.setOnClickListener {
            val action =
                ListFragmentDirections.actionListFragmentToRelationshipFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(node: List<NodeDbEntity>) {
        this.nodeList = node
        notifyDataSetChanged()
    }

    fun setRelationship(relationship: List<RelationshipDbEntity>) {
        this.relationshipList = relationship
        notifyDataSetChanged()
    }
}
