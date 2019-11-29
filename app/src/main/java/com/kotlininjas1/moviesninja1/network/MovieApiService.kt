package com.kotlininjas1.moviesninja1.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.kotlininjas1.moviesninja1.utils.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/*OkHttp for basic retrofit setup*/
//TODO: Get rid of this logging
val interceptor: HttpLoggingInterceptor =
    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

val client: OkHttpClient = OkHttpClient.Builder()
    .callTimeout(30, TimeUnit.SECONDS)
    .connectTimeout(30, TimeUnit.SECONDS)
    .readTimeout(30, TimeUnit.SECONDS)
    .addInterceptor(interceptor)
    .writeTimeout(30, TimeUnit.SECONDS)
    .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(Constants.BASE_URL)
    .client(client)
    .build()

/**
 * A public interface that exposes the [getMoviesAsync] method
 */
interface MovieApiService {
    /**
     * Returns a Coroutine [Deferred] [Result] which can be fetched with await() if
     * in a Coroutine scope.
     */
    @GET("4/{endpoint}/{type}")
    fun getMoviesAsync(
        @Path("endpoint") endpoint: String,
        @Path("type") type: String,
        @Query("sort_by") sortBy: String?,
        @Query("api_key") apiKey: String
    ): Deferred<Result> // The Coroutine Call Adapter allows us to return a Deferred, a Job with a result
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object MovieApi {
    val movieRetrofitService: MovieApiService by lazy { retrofit.create(MovieApiService::class.java) }
}