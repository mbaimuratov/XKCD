package com.example.xkcd

import com.google.gson.GsonBuilder
import io.reactivex.rxjava3.core.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class ComicRxService {

    private var comicEndPoint: ComicEndPoint

    init {
        val okHttpClient = OkHttpClient.Builder().build()

        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://xkcd.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

        comicEndPoint = retrofit.create(ComicEndPoint::class.java)
    }

    fun getComicWithId(id: Int): Observable<Comic> = comicEndPoint.getComic(id)
}