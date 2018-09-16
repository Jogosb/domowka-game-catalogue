package com.filipradon.data.repository

import com.filipradon.data.model.BeerEntity
import io.reactivex.Observable

interface BeersDataStore {

    fun getBeers(): Observable<List<BeerEntity>>

}