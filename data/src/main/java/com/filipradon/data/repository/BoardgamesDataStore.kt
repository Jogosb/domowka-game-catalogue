package com.filipradon.data.repository

import com.filipradon.data.model.BoardgameEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface BoardgamesDataStore {

    fun getBoardgames(): Observable<List<BoardgameEntity>>

    fun saveBoardgames(boardgames: List<BoardgameEntity>): Completable

    fun clearBoardgames(): Completable

}