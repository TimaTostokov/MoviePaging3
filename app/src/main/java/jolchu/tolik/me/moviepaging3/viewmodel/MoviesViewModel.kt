package jolchu.tolik.me.moviepaging3.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import jolchu.tolik.me.moviepaging3.paging.MoviesPagingSource
import jolchu.tolik.me.moviepaging3.repository.ApiRepository
import jolchu.tolik.me.moviepaging3.response.MovieDetailsResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: ApiRepository) : ViewModel() {

    val loading = MutableLiveData<Boolean>()

    val moviesList = Pager(PagingConfig(1)) {
        MoviesPagingSource(repository)
    }.flow.cachedIn(viewModelScope)


    val detailsMovie = MutableLiveData<MovieDetailsResponse>()
    fun loadDetailsMovie(id: Int) = viewModelScope.launch {
        loading.postValue(true)
        val response = repository.getMovieDetails(id)
        if (response.isSuccessful) {
            detailsMovie.postValue(response.body())
        }
        loading.postValue(false)
    }

}