package com.filipradon.data.store

import com.nhaarman.mockitokotlin2.mock
import org.junit.Test
import kotlin.test.assertEquals

class BoardgamesDataStoreFactoryTest {

    private val cacheStore = mock<BoardgamesCacheDataStore>()
    private val remoteStore = mock<BoardgamesRemoteDataStore>()
    private val factory = BoardgamesDataStoreFactory(cacheStore, remoteStore)

    @Test
    fun `assert getDataStore returns remote store when cache expired`() {
        assertEquals(remoteStore, factory.getDataStore(true, true))
    }

    @Test
    fun `assert getDataStore returns remote store when boardgames not cached`() {
        assertEquals(remoteStore, factory.getDataStore(false, false))
    }

    @Test
    fun `assert getDataStore returns cache store when boardgames are cached and not expired`() {
        assertEquals(cacheStore, factory.getDataStore(true, false))
    }

    @Test
    fun `assert getCacheStore returns cache store`() {
        assertEquals(cacheStore, factory.getCacheDataStore())
    }

}