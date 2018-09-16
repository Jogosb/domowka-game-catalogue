package com.filipradon.data.store

import com.filipradon.data.model.BeerEntity
import com.filipradon.data.repository.BeersDataStore
import com.filipradon.data.repository.BeersRemote
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class BeersRemoteDataStore @Inject constructor(
        private val beersRemote: BeersRemote): BeersDataStore {

    override fun getBeers(): Observable<List<BeerEntity>> {
        return beersRemote.getBeers()
    }

    override fun saveBeers(beers: List<BeerEntity>): Completable {
        throw UnsupportedOperationException("Saving beers is not supported!")
    }

    override fun clearBeers(): Completable {
        throw UnsupportedOperationException("Clearing beers is not supported!")
    }
}