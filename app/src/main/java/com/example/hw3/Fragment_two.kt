package com.example.hw3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.hw3.databinding.FragmentTwoBinding

class Fragment_two: Fragment() {

    private val dataModel: DataModel by activityViewModels()
    lateinit var binding: FragmentTwoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTwoBinding.inflate(inflater)
        if(dataModel.secondNumber.value != null) {
            binding.input2.setText(dataModel.secondNumber.value.toString())
        }
        val mainActivity = (activity as MainActivity?)!!
        mainActivity.binding.btnStep2.visibility = View.VISIBLE
        mainActivity.binding.btnStep3.visibility = View.GONE
        mainActivity.binding.btnStep4.visibility = View.GONE
        mainActivity.binding.btnNext.visibility = View.VISIBLE
        mainActivity.binding.btnBack.visibility = View.VISIBLE
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dataModel.secondNumber.value = binding.input2.text.toString()
    }
}