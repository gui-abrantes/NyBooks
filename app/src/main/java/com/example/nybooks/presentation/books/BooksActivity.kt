package com.example.nybooks.presentation.books

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nybooks.NyBooks
import com.example.nybooks.R
import com.example.nybooks.presentation.base.BaseActivity
import com.example.nybooks.presentation.details.BookDetailsActivity
import kotlinx.android.synthetic.main.activity_books.*
import kotlinx.android.synthetic.main.include_toolbar.*
import javax.inject.Inject


class BooksActivity : BaseActivity() {

    @Inject lateinit var viewModel: BooksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as NyBooks).appComponent.mainComponent().create().inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        setupToolBar(toolbarMain, R.string.books_title)

        viewModel.booksLiveData.observe(this,  {
            it?.let { books ->
                with(recyclerBooks) {
                    layoutManager =
                        LinearLayoutManager(this@BooksActivity, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = BooksAdapter(books) { book ->
                        val intent = BookDetailsActivity.getStartIntent(
                            this@BooksActivity,
                            book.title,
                            book.description
                        )

                        this@BooksActivity.startActivity(intent)
                    }
                }

            }
        })

        viewModel.viewFlipperLiveData.observe(this, { it ->
            it?.let { viewFlipper ->
                viewFlipperBooks.displayedChild = viewFlipper.first
                viewFlipper.second?.let { errorMessageResId ->
                    textViewError.text = getString(errorMessageResId)
                }
            }
        })

        viewModel.getBooks()
    }


}