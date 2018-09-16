package com.filipradon.data.store

import com.filipradon.data.model.BoardgameEntity
import com.filipradon.data.repository.BoardgamesCache
import com.filipradon.data.repository.BoardgamesDataStore
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class BoardgamesCacheDataStore @Inject constructor(
        private val boardgamesCache: BoardgamesCache) : BoardgamesDataStore {

    override fun getBoardgames(): Observable<List<BoardgameEntity>> {
        return boardgamesCache.getBoardgames()
    }

    override fun saveBoardgames(boardgames: List<BoardgameEntity>): Completable {
        return boardgamesCache.saveBoardgames(boardgames)
                .andThen(boardgamesCache.setLastCacheTime(System.currentTimeMillis()))
    }

    override fun clearBoardgames(): Completable {
        return boardgamesCache.clearBoardgames()
    }
}