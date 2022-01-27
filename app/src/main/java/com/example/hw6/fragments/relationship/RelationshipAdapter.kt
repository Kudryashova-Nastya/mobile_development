package com.example.hw6.fragments.relationship

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hw6.R
import com.example.hw6.model.nodes.room.entities.NodeDbEntity
import com.example.hw6.model.relationships.room.entities.RelationshipDbEntity
import kotlinx.android.synthetic.main.relationship.view.*

class RelationshipAdapter(
    nodeNow: NodeDbEntity,
    isChildrenFilter: Boolean,
    fragmentManager: FragmentManager
) :
    RecyclerView.Adapter<RelationshipAdapter.MyViewHolder>() {

    private var nodeList = emptyList<NodeDbEntity?>()
    private var relationshipList = emptyList<RelationshipDbEntity>()

    private var ourNode = nodeNow
    private var childrenFilter = isChildrenFilter
    private var fragment = fragmentManager


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
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

            if (checkRelationship(ourNode, currentItem)) {
                holder.itemView.relationship.setBackgroundColor(Color.parseColor("#97F5A2"))

//                holder.itemView.setOnClickListener {
//                    var dialogDel = DeleteRelationFragment(ourNode, currentItem, childrenFilter)
//                    dialogDel.show(fragment, "tag")
//                    Toast.makeText(
//                        holder.itemView.context,
//                        "success #${position + 1}",
//                        Toast.LENGTH_SHORT
//                    ).show()
//
//                }
            }
//            else {
//                holder.itemView.setOnClickListener {
//                    var dialogAdd = AddRelationFragment(ourNode, currentItem, childrenFilter)
//                    dialogAdd.show(fragment, "tag")
//                }
//            }
        } else {
            holder.itemView.textRelationship1.text =
                "id: " + currentItem?.id.toString() + " | value: " + currentItem?.value.toString()
            holder.itemView.textRelationship2.text =
                "id: " + ourNode.id.toString() + " | value: " + ourNode.value.toString()

            if (checkRelationship(currentItem, ourNode)) {
                holder.itemView.relationship.setBackgroundColor(Color.parseColor("#97F5A2"))
            }
        }
    }

    private fun checkRelationship(parentNode: NodeDbEntity?, childNode: NodeDbEntity?): Boolean {
        if (relationshipList.isNullOrEmpty().not()) {
            val checkOurNodeIsParent = parentNode?.id in relationshipList.map { it -> it.parent }
//                Log.d("TAG00000000001", checkOurNodeIsParent.toString())
            if (checkOurNodeIsParent) {
                val ourNodeChildren = relationshipList.filter { it.parent == parentNode?.id }
                val checkOurNodeChildren =
                    childNode?.id in ourNodeChildren.map { it -> it.child }
//                    Log.d("TAG000000000002", ourNodeChildren.toString())
                if (checkOurNodeChildren) {
//                        Log.d("TAG000000000003", checkOurNodeChildren.toString())
                    return true
                }
            }
        }
        return false
    }

    fun setData(node: List<NodeDbEntity?>) {
        this.nodeList = node.filter { it!!.id != ourNode.id }
//        this.nodeList = node as MutableList<NodeDbEntity?>
        notifyDataSetChanged()
    }

    fun setRelationship(relationship: List<RelationshipDbEntity>) {
        this.relationshipList = relationship
        notifyDataSetChanged()
    }
}