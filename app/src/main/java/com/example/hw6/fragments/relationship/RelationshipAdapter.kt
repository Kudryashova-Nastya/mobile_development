package com.example.hw6.fragments.relationship

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
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

            // фильтрация
            if (checkRelationship(currentItem, ourNode)) {
                holder.itemView.rowRelationshipLayout.visibility = GONE
                holder.itemView.relationship.visibility = GONE
            }

            if (checkRelationship(ourNode, currentItem)) {
                holder.itemView.relationship.setBackgroundColor(Color.parseColor("#97F5A2"))
                holder.itemView.setOnClickListener {
                    val dialogDel = DeleteRelationshipFragment(ourNode, currentItem!!)
                    dialogDel.show(fragment, "show")
//                    Toast.makeText(holder.itemView.context, "успешно удалено", Toast.LENGTH_SHORT).show()
                    notifyDataSetChanged()
                }
            } else {
                holder.itemView.relationship.setBackgroundColor(Color.WHITE)
                holder.itemView.setOnClickListener {
                    val dialogAdd = AddRelationshipFragment(ourNode, currentItem!!)
                    dialogAdd.show(fragment, "show")
                }
            }
        } else {
            holder.itemView.textRelationship1.text =
                "id: " + currentItem?.id.toString() + " | value: " + currentItem?.value.toString()
            holder.itemView.textRelationship2.text =
                "id: " + ourNode.id.toString() + " | value: " + ourNode.value.toString()

            // фильтрация
            if (checkRelationship(ourNode, currentItem)) {
                holder.itemView.rowRelationshipLayout.visibility = GONE
                holder.itemView.relationship.visibility = GONE
            }

            if (checkRelationship(currentItem, ourNode)) {
                holder.itemView.relationship.setBackgroundColor(Color.parseColor("#97F5A2"))
                holder.itemView.setOnClickListener {
                    val dialogDel = DeleteRelationshipFragment(currentItem!!, ourNode)
                    dialogDel.show(fragment, "show")
                    notifyDataSetChanged()
                }
            } else {
                holder.itemView.relationship.setBackgroundColor(Color.WHITE)
                holder.itemView.setOnClickListener {
                    val dialogAdd = AddRelationshipFragment(currentItem!!, ourNode)
                    dialogAdd.show(fragment, "show")
                }
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
        notifyDataSetChanged()
    }

    fun setRelationship(relationship: List<RelationshipDbEntity>) {
        this.relationshipList = relationship
        notifyDataSetChanged()
    }
}