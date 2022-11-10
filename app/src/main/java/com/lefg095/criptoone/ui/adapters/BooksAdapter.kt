package com.lefg095.criptoone.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lefg095.criptoone.R
import com.lefg095.criptoone.databinding.ItemBookBinding
import com.lefg095.criptoone.domain.Book
import com.lefg095.criptoone.ui.callbacks.ItemBookCallBack
import com.lefg095.criptoone.util.getIdResource
import com.squareup.picasso.Picasso

class BooksAdapter(
    val books: List<Book>,
    val mCallback: ItemBookCallBack
    ) : RecyclerView.Adapter<BooksAdapter.BooksViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BooksViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BooksViewHolder(layoutInflater.inflate(R.layout.item_book, parent, false))
    }

    override fun onBindViewHolder(holder: BooksViewHolder, i: Int) {
        holder.bind(books[i], mCallback)
    }

    override fun getItemCount(): Int = books.size

    class BooksViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = ItemBookBinding.bind(view)
        fun bind(book: Book, mCallback: ItemBookCallBack){
            binding.tvBookName.text = book.book.uppercase()
            Picasso.get().load(getIdResource(book.book.split("_")[0])).into(binding.imageView)
            binding.lyBook.setOnClickListener {
                mCallback.showDetailClicket(book)
            }
        }
    }
}