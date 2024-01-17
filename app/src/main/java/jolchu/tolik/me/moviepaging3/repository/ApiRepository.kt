package jolchu.tolik.me.moviepaging3.repository

import jolchu.tolik.me.moviepaging3.api.ApiServices
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val apiServices: ApiServices,
) {
    suspend fun getPopularMoviesList(page: Int) = apiServices.getPopularMoviesList(page)
    suspend fun getMovieDetails(id: Int) = apiServices.getMovieDetails(id)
}