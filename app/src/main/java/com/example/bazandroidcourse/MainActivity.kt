package com.example.bazandroidcourse

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bazandroidcourse.data.entities.BookModel
import com.example.bazandroidcourse.databinding.ActivityMainBinding
import com.example.bazandroidcourse.ui.adapter.BooksAdapter
import com.example.bazandroidcourse.ui.viewmodel.BooksViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}