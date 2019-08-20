package com.filipradon.remote.service

import com.filipradon.remote.model.BeersResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface DomowkaBeersService {

    @GET("url") // ADD valid URL when I'll have one!
    fun getBeers(): Observable<BeersResponse>


}