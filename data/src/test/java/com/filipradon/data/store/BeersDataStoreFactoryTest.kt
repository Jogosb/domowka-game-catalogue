package com.filipradon.data.store

import com.nhaarman.mockitokotlin2.mock
import org.junit.Test
import kotlin.test.assertEquals

class BeersDataStoreFactoryTest {

    private val cacheStore = mock<BeersCacheDataStore>()
    private val remoteStore = mock<BeersRemoteDataStore>()
    private val factory = BeersDataStoreFactory(cacheStore, remoteStore)

    @Test
    fun `assert getDataStore returns remote store when cache expired`() {
        assertEquals(remoteStore, factory.getDataStore(true, true))
    }

    @Test
    fun `assert getDataStore returns remote store when beers not cached`() {
        assertEquals(remoteStore, factory.getDataStore(false, false))
    }

    @Test
    fun `assert getDataStore returns cache store when beers are cached and not expired`() {
        assertEquals(cacheStore, factory.getDataStore(true, false))
    }

    @Test
    fun `assert getCacheStore returns cache store`() {
        assertEquals(cacheStore, factory.getCacheDataStore())
    }
}