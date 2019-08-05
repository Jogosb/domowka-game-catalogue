package com.filipradon.data

import com.filipradon.data.mapper.BoardgameMapper
import com.filipradon.data.model.BoardgameEntity
import com.filipradon.data.repository.BoardgamesCache
import com.filipradon.data.repository.BoardgamesDataStore
import com.filipradon.data.store.BoardgamesDataStoreFactory
import com.filipradon.data.test.factory.BoardgameFactory
import com.filipradon.domain.model.Boardgame
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class BoardgamesDataRepositoryTest {

    private val mapper = mock<BoardgameMapper>()
    private val factory = mock<BoardgamesDataStoreFactory>()
    private val store = mock<BoardgamesDataStore>()
    private val cache = mock<BoardgamesCache>()
    private val repository = BoardgamesDataRepository(mapper, cache, factory)

    @Before
    fun setUp() {
        stubFactoryGetDataStore()
        stubFactoryGetCacheDataStore()
        stubIsCacheExpired(Single.just(false))
        stubAreBoardgamesCached(Single.just(false))
        stubSaveBoardgames(Completable.complete())
    }

    @Test
    fun `assert getBoardgames completes`() {
        stubGetBoardgames(Observable.just(listOf(BoardgameFactory.makeBoardgameEntity())))
        stubMapper(BoardgameFactory.makeBoardgame(), any())

        repository.getBoardgames().test().assertComplete()
    }

    @Test
    fun `assert getBoardgames returns data`() {
        val boardgameEntity = BoardgameFactory.makeBoardgameEntity()
        val boardgame = BoardgameFactory.makeBoardgame()

        stubGetBoardgames(Observable.just(listOf(boardgameEntity)))
        stubMapper(boardgame, boardgameEntity)

        repository.getBoardgames().test().assertValue(listOf(boardgame))
    }

    private fun stubIsCacheExpired(single: Single<Boolean>) {
        whenever(cache.isBoardgamesCacheExpired()) doReturn single
    }

    private fun stubAreBoardgamesCached(single: Single<Boolean>) {
        whenever(cache.areBoardgamesCached()) doReturn single
    }

    private fun stubMapper(model: Boardgame, entity: BoardgameEntity) {
        whenever(mapper.mapFromEntity(entity)) doReturn model
    }

    private fun stubGetBoardgames(observable: Observable<List<BoardgameEntity>>) {
        whenever(store.getBoardgames()) doReturn observable
    }

    private fun stubFactoryGetDataStore() {
        whenever(factory.getDataStore(any(), any())) doReturn store
    }

    private fun stubFactoryGetCacheDataStore() {
        whenever(factory.getCacheDataStore()) doReturn store
    }

    private fun stubSaveBoardgames(completable: Completable) {
        whenever(store.saveBoardgames(any())) doReturn completable
    }
}

