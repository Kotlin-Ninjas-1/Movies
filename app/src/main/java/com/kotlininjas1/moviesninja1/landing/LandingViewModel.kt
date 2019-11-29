package com.kotlininjas1.moviesninja1.landing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlininjas1.moviesninja1.network.Movie
import com.kotlininjas1.moviesninja1.network.MovieApi
import com.kotlininjas1.moviesninja1.network.Result
import com.kotlininjas1.moviesninja1.utils.Constants
import kotlinx.coroutines.launch

enum class MovieApiStatus { LOADING, ERROR, DONE }

class LandingViewModel : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<MovieApiStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<MovieApiStatus>
        get() = _status

    // Internally, we use a MutableLiveData, because we will be updating the MovieList
    // with new values
    private val _movieList = MutableLiveData<List<Movie>>()

    // The external LiveData interface to the list is immutable, so only this class can modify
    val movieList: LiveData<List<Movie>>
        get() = _movieList

    /**
     * Call getMovieList() on init so we can display status immediately.
     */
    init {
        getMovieList()
    }

    /**
     * Gets the movie list information from the Movies API Retrofit service and updates the
     * [Result] [List] and [Result] [LiveData]. The Retrofit service returns a
     * coroutine Deferred, which we await to get the result of the transaction.
     */
    private fun getMovieList() {
        viewModelScope.launch {
            // Get the Deferred object for our Retrofit request
            val getMoviesDeferred =
                MovieApi.movieRetrofitService.getMoviesAsync(
                    "discover", "movie", "popularity.desc",
                    Constants.API_KEY
                )
            try {
                _status.value = MovieApiStatus.LOADING
                val result = getMoviesDeferred.await()
                _status.value = MovieApiStatus.DONE
                _movieList.value = result.movieList
            } catch (e: Exception) {
                _status.value = MovieApiStatus.ERROR
                _movieList.value = ArrayList()
            }
        }
    }
}