package com.example.xkcd

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ComicEndPoint {

    @GET("{id}/info.0.json")
    fun getComic(@Path("id") comicNum: Int): Observable<Comic>
}