package com.kotlininjas1.moviesninja1

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class SearchResultActivity : AppCompatActivity() {
    var mTextViewSearchResult: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)
        mTextViewSearchResult = findViewById(R.id.textViewSearchResult)
        if (Intent.ACTION_SEARCH.equals(getIntent().getAction())){
            handleSearch(getIntent().getStringExtra(SearchManager.QUERY))
        }
    }

    private fun handleSearch(String: String?) {
        mTextViewSearchResult?.setText(String)

    }


}
