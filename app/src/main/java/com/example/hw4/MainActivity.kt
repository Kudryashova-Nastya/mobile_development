package com.example.hw4

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hw4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

//    private val horizontalLinearLayoutManager: LinearLayoutManager =
//        LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    private val verticalLinearLayoutManager: LinearLayoutManager =
        LinearLayoutManager(this, RecyclerView.VERTICAL, false)

//    private val gridLinearLayoutManager: GridLayoutManager =
//        GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)



    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.itemPerson.layoutManager = verticalLinearLayoutManager
        binding.itemPerson.adapter = Adapter(Holder.createCollectionPerson(),::showSnackbar, ::showSnackbarLike)
    }

    private fun showSnackbar(person: Person): Unit{
        Snackbar.make(binding.root, "Нажата карточка: " + person.name, 2000).show()
    }
    private  fun showSnackbarLike(person: Person): Unit{
        Snackbar.make(binding.root, "Нажат лайк:  " + person.name, 2000).show()
    }
}