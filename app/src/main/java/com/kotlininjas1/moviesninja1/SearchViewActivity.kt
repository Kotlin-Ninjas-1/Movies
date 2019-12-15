package com.kotlininjas1.moviesninja1

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_search_view.*

class SearchViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_view)
    }


        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            val inflater = menuInflater
            inflater.inflate(R.menu.menu_search, menu)

            val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
            val searchItem = menu?.findItem(R.id.search)
            val searchView = searchItem?.actionView as SearchView

            searchView.setSearchableInfo(manager.getSearchableInfo(componentName))

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    searchView.clearFocus()
                    searchView.setQuery(query,  false)
                    searchItem.collapseActionView()
                    Toast.makeText(this@SearchViewActivity, "Looking for $query", Toast.LENGTH_LONG).show()
                    return true

                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    return false
                }

            })
            return true


    }
}
