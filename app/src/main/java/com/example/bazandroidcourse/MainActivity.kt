package com.example.bazandroidcourse

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bazandroidcourse.data.entities.BookModel
import com.example.bazandroidcourse.databinding.ActivityMainBinding
import com.example.bazandroidcourse.ui.adapter.BooksAdapter
import com.example.bazandroidcourse.ui.viewmodel.BooksViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val adapter = BooksAdapter()
    val viewModel: BooksViewModel  = BooksViewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getAllBooks()
        initList()
        addObservers()
    }

    fun initList(){
        binding.recyclerBooks.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )
        binding.recyclerBooks.adapter = adapter

    }
    fun addObservers(){
        viewModel.allBooks.observe(this){
            adapter.submitList(it)
        }
    }


}