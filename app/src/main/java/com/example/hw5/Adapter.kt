package com.example.hw5

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hw5.databinding.ItemTemplateBinding


class Adapter(
    var personList: MutableList<Person>
) : RecyclerView.Adapter<Adapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemTemplateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val person = personList[position]
        holder.bind(person)
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    inner class Holder internal constructor(
        private val binding: ItemTemplateBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(person: Person) = binding.run {
            info.text = person.text
        }
    }
}