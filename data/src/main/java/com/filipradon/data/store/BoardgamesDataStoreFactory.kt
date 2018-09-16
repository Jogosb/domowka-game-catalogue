package com.filipradon.data.store

import com.filipradon.data.repository.BoardgamesDataStore
import javax.inject.Inject

open class BoardgamesDataStoreFactory @Inject constructor(
        private val boardgamesCacheDataStore: BoardgamesCacheDataStore,
        private val boardgamesRemoteDataStore: BoardgamesRemoteDataStore) {

    open fun getDataStore(boardgamesCached: Boolean,
                          cacheExpired: Boolean): BoardgamesDataStore {
        return if (boardgamesCached && !cacheExpired) boardgamesCacheDataStore
        else boardgamesRemoteDataStore
    }

    open fun getCacheDataStore(): BoardgamesDataStore {
        return boardgamesCacheDataStore
    }

}