package com.filipradon.data.repository

import com.filipradon.data.model.BeerEntity
import io.reactivex.Observable

interface BeersRemote {

    fun getBeers(): Observable<List<BeerEntity>>

}