package com.filipradon.data

import com.filipradon.data.mapper.BeerMapper
import com.filipradon.data.model.BeerEntity
import com.filipradon.data.repository.BeersCache
import com.filipradon.data.repository.BeersDataStore
import com.filipradon.data.store.BeersDataStoreFactory
import com.filipradon.data.test.factory.BeerFactory
import com.filipradon.domain.model.Beer
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class BeersDataRepositoryTest {

    private val mapper = mock<BeerMapper>()
    private val factory = mock<BeersDataStoreFactory>()
    private val store = mock<BeersDataStore>()
    private val cache = mock<BeersCache>()
    private val repository = BeersDataRepository(mapper, cache, factory)

    @Before
    fun setUp() {
        stubFactoryGetDataStore()
        stubFactoryGetCacheDataStore()
        stubIsCacheExpired(Single.just(false))
        stubAreBeersCached(Single.just(false))
        stubSaveBeers(Completable.complete())
    }

    @Test
    fun `assert that getBeers completes`() {
        stubGetBeers(
            Observable.just(
                listOf(
                    BeerFactory.makeBeerEntity(),
                    BeerFactory.makeBeerEntity()
                )
            )
        )

        stubMapper(BeerFactory.makeBeer(), any())

        repository.getBeers().test().assertComplete()
    }

    @Test
    fun `assert that getBeers returns data`() {
        val beerEntity = BeerFactory.makeBeerEntity()
        val beer = BeerFactory.makeBeer()

        stubGetBeers(Observable.just(listOf(beerEntity)))
        stubMapper(beer, beerEntity)

        repository.getBeers().test().assertValue(listOf(beer))
    }

    private fun stubIsCacheExpired(single: Single<Boolean>) {
        whenever(cache.isBeersCacheExpired()).thenReturn(single)
    }

    private fun stubAreBeersCached(single: Single<Boolean>) {
        whenever(cache.areBeersCached()).thenReturn(single)
    }

    private fun stubMapper(model: Beer, entity: BeerEntity) {
        whenever(mapper.mapFromEntity(entity)).thenReturn(model)
    }

    private fun stubGetBeers(observable: Observable<List<BeerEntity>>) {
        whenever(store.getBeers()).thenReturn(observable)
    }

    private fun stubFactoryGetDataStore() {
        whenever(factory.getDataStore(any(), any())).thenReturn(store)
    }

    private fun stubFactoryGetCacheDataStore() {
        whenever(factory.getCacheDataStore()).thenReturn(store)
    }

    private fun stubSaveBeers(completable: Completable) {
        whenever(store.saveBeers(any())).doReturn(completable)
    }
}
