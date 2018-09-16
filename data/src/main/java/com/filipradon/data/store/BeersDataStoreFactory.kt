package com.filipradon.data.store

import com.filipradon.data.repository.BeersDataStore
import javax.inject.Inject

class BeersDataStoreFactory @Inject constructor(
        private val beersCacheDataStore: BeersCacheDataStore,
        private val beersRemoteDataStore: BeersRemoteDataStore) {

    open fun getDataStore(beersCached: Boolean,
                          cacheExpired: Boolean): BeersDataStore {
        return if (beersCached && !cacheExpired) beersCacheDataStore
        else beersRemoteDataStore
    }

    open fun getCacheDataStore(): BeersDataStore {
        return beersCacheDataStore
    }

}