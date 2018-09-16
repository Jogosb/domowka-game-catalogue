package com.filipradon.data.repository

import com.filipradon.data.model.BeerEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface BeersCache {

    fun clearBeers(): Completable

    fun saveBeers(beers: List<BeerEntity>): Completable

    fun getBeers(): Observable<List<BeerEntity>>

    fun areBeersCached(): Single<Boolean>

    fun setLastCacheTime(lastCache: Long): Completable

    fun isBeersCacheExpired(): Single<Boolean>

}