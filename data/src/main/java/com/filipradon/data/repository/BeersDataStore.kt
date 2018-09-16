package com.filipradon.data.repository

import com.filipradon.data.model.BeerEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface BeersDataStore {

    fun getBeers(): Observable<List<BeerEntity>>

    fun saveBeers(beers: List<BeerEntity>): Completable

    fun clearBeers(): Completable

}