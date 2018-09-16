package com.filipradon.data.store

import com.filipradon.data.model.BeerEntity
import com.filipradon.data.repository.BeersCache
import com.filipradon.data.repository.BeersDataStore
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class BeersCacheDataStore @Inject constructor(
        private val beersCache: BeersCache): BeersDataStore {

    override fun getBeers(): Observable<List<BeerEntity>> {
        return beersCache.getBeers()
    }

    override fun saveBeers(beers: List<BeerEntity>): Completable {
        return beersCache.saveBeers(beers)
                .andThen(beersCache.setLastCacheTime(System.currentTimeMillis()))
    }

    override fun clearBeers(): Completable {
        return beersCache.clearBeers()
    }
}