package com.example.hw3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.hw3.databinding.FragmentThreeBinding


class Fragment_three: Fragment() {

    private val dataModel: DataModel by activityViewModels()
    private  lateinit var binding: FragmentThreeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentThreeBinding.inflate(inflater)
        binding.plus.setOnClickListener {
            dataModel.action.value = "+"
            toFragmentFour()
        }
        binding.minus.setOnClickListener {
            dataModel.action.value = "-"
            toFragmentFour()
        }
        binding.multiplication.setOnClickListener {
            dataModel.action.value = "*"
            toFragmentFour()
        }
        binding.division.setOnClickListener {
            dataModel.action.value = "/"
            toFragmentFour()
        }
        val mainActivity = (activity as MainActivity?)!!
        mainActivity.binding.btnStep2.visibility = View.VISIBLE
        mainActivity.binding.btnStep3.visibility = View.VISIBLE
        mainActivity.binding.btnStep4.visibility = View.GONE
        mainActivity.binding.btnNext.visibility = View.GONE
        mainActivity.binding.btnBack.visibility = View.VISIBLE
        return binding.root
    }

    private fun toFragmentFour() {
        var fr = parentFragmentManager?.beginTransaction()
        fr?.replace(R.id.fragment_container, Fragment_four())
        fr?.addToBackStack("4")
        fr?.commit()
    }

}