package com.example.hw3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.hw3.databinding.FragmentOneBinding

class Fragment_one: Fragment() {

    private val dataModel: DataModel by activityViewModels()
    lateinit var binding: FragmentOneBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOneBinding.inflate(inflater)
        if(dataModel.firstNumber.value != null) {
            binding.input1.setText(dataModel.firstNumber.value.toString())
        }
        val mainActivity = (activity as MainActivity?)!!
        mainActivity.binding.btnStep2.visibility = View.GONE
        mainActivity.binding.btnStep3.visibility = View.GONE
        mainActivity.binding.btnStep4.visibility = View.GONE
        mainActivity.binding.btnNext.visibility = View.VISIBLE
        mainActivity.binding.btnBack.visibility = View.GONE
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dataModel.firstNumber.value = binding.input1.text.toString()
    }
}