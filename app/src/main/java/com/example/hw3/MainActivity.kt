package com.example.hw3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.hw3.databinding.ActivityMainBinding


class MainActivity: AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val fragment = Fragment_one()
        val transaction = supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
        transaction.commit()


        binding.btnStep1.setOnClickListener {
            replaceFragment(Fragment_one(),"1")
        }
        binding.btnStep2.setOnClickListener {
            replaceFragment(Fragment_two(), "2")
        }
        binding.btnStep3.setOnClickListener {
            replaceFragment(Fragment_three(),"3")
        }
        binding.btnStep4.setOnClickListener {
            replaceFragment(Fragment_four(),"4")
        }

        binding.btnNext.setOnClickListener {
            when (supportFragmentManager.fragments.last()) {
                is Fragment_one -> replaceFragment(Fragment_two(), "2")
                is Fragment_two -> replaceFragment(Fragment_three(), "3")
                is Fragment_three -> replaceFragment(Fragment_four(), "4")
            }
        }
        
        binding.btnBack.setOnClickListener {
            supportFragmentManager.popBackStack()
        }
    }

    private fun replaceFragment(fragment: Fragment, number:String) {
        val transaction = supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(number)
        transaction.commit()
    }
}


