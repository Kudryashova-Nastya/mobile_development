package com.example.hw6.fragments.relationship

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hw6.R
import com.example.hw6.model.nodes.room.entities.NodeDbEntity
import kotlinx.android.synthetic.main.relationship.view.*

class RelationshipAdapter(
    nodeNow: NodeDbEntity,
    isChildrenFilter: Boolean,
    fragmentManager: FragmentManager
) :
    RecyclerView.Adapter<RelationshipAdapter.MyViewHolder>() {

    private var nodeList = mutableListOf<NodeDbEntity?>()
    private var ourNode = nodeNow
    private var childrenFilter = isChildrenFilter
    private var fragment = fragmentManager


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position: Int = adapterPosition
                Toast.makeText(itemView.context, "тык-тык", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.relationship, parent, false)
        return MyViewHolder(v)

    }

    override fun getItemCount(): Int {
        return nodeList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = nodeList[position]

        if (childrenFilter) {
            holder.itemView.textRelationship1.text =
                "id: " + ourNode.id.toString() + " | value: " + ourNode.value.toString()
            holder.itemView.textRelationship2.text =
                "id: " + currentItem?.id.toString() + " | value: " + currentItem?.value.toString()
        } else {
            holder.itemView.textRelationship1.text =
                "id: " + currentItem?.id.toString() + " | value: " + currentItem?.value.toString()
            holder.itemView.textRelationship2.text =
                "id: " + ourNode.id.toString() + " | value: " + ourNode.value.toString()
        }
    }

    fun setData(node: List<NodeDbEntity?>) {
        this.nodeList = node.filter { it!!.id != ourNode.id } as MutableList<NodeDbEntity?>
//        this.nodeList = node as MutableList<NodeDbEntity?>
        notifyDataSetChanged()
    }
}