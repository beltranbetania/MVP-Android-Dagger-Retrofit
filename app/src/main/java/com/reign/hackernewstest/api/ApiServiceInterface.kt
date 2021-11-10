package com.reign.hackernewstest.api


import com.reign.hackernewstest.data.networkData.ArticleResponse
import com.reign.hackernewstest.util.Constants
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceInterface {

    @GET("search_by_date")
    fun getArticles(@Query("query") id: String): Observable<ArticleResponse>

    companion object Factory {
        fun create(): ApiServiceInterface {
            val retrofit = retrofit2.Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Constants.BASE_URL)
                    .build()

            return retrofit.create(ApiServiceInterface::class.java)
        }
    }
}