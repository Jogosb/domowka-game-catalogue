package com.filipradon.data.repository

import com.filipradon.data.model.BoardgameEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface BoardgamesCache {

    fun clearBoardgames(): Completable

    fun saveBoardgames(boardgames: List<BoardgameEntity>): Completable

    fun getBoardgames(): Observable<List<BoardgameEntity>>

    fun areBoardgamesCached(): Single<Boolean>

    fun setLastCacheTime(lastCache: Long): Completable

    fun isBoardgamesCacheExpired(): Single<Boolean>
    
}