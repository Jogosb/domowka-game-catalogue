package com.filipradon.data.store

import com.filipradon.data.model.BoardgameEntity
import com.filipradon.data.repository.BoardgamesDataStore
import com.filipradon.data.repository.BoardgamesRemote
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

open class BoardgamesRemoteDataStore @Inject constructor(
        private val boardgamesRemote: BoardgamesRemote): BoardgamesDataStore {

    override fun getBoardgames(): Observable<List<BoardgameEntity>> {
        return boardgamesRemote.getBoardgames()
    }

    override fun saveBoardgames(boardgames: List<BoardgameEntity>): Completable {
        throw UnsupportedOperationException("Saving boardgames is not supported!")
    }

    override fun clearBoardgames(): Completable {
        throw UnsupportedOperationException("Clearing boardgames is not supported!")
    }

}
