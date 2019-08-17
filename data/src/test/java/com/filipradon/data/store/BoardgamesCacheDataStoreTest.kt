package com.filipradon.data.store

import com.filipradon.data.model.BoardgameEntity
import com.filipradon.data.repository.BoardgamesCache
import com.filipradon.data.test.factory.BoardgameFactory
import com.filipradon.domain.model.Boardgame
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Assert.*
import org.junit.Test

class BoardgamesCacheDataStoreTest {

    private val cache: BoardgamesCache = mock()
    private val store = BoardgamesCacheDataStore(cache)

    @Test
    fun `assert that get boardgames completes`(){
        stubBoardgamesCacheGetBoardgames(Observable.just(listOf(BoardgameFactory.makeBoardgameEntity())))
        store.getBoardgames().test().assertComplete()
    }

    @Test
    fun `assert that get boardgames return data`(){
        val data = listOf(BoardgameFactory.makeBoardgameEntity())
        stubBoardgamesCacheGetBoardgames(Observable.just(data))
        store.getBoardgames().test().assertValue(data)
    }

    private fun stubBoardgamesCacheGetBoardgames(observable: Observable<List<BoardgameEntity>>) {
        whenever(cache.getBoardgames()) doReturn observable
    }

}