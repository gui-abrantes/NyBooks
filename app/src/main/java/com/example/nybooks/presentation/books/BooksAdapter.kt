package com.example.nybooks.presentation.books

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.nybooks.R
import com.example.nybooks.data.model.Book
import kotlinx.android.synthetic.main.item_book.view.*

class BooksAdapter(
    private val books: List<Book>,
    val onItemClickListener: ((book: Book) -> Unit),
) : RecyclerView.Adapter<BooksAdapter.BooksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BooksViewHolder(view, onItemClickListener)
    }

    override fun getItemCount() = books.count()

    override fun onBindViewHolder(viewholder: BooksViewHolder, position: Int) {
        viewholder.bindView(books[position])
    }

    class BooksViewHolder(
        itemView: View,
        private val onItemClickListener: ((book: Book) -> Unit)) :
        RecyclerView.ViewHolder(itemView) {
        private val title = itemView.textTitle
        private val author = itemView.textAuthor

        fun bindView(book: Book) {
            title.text = book.title
            author.text = book.author

            itemView.setOnClickListener {
                onItemClickListener.invoke(book)
            }
        }
    }
}