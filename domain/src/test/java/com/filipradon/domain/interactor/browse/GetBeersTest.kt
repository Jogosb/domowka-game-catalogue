package com.filipradon.domain.interactor.browse

import com.filipradon.domain.executor.PostExecutionThread
import com.filipradon.domain.interactor.test.BeerDataFactory
import com.filipradon.domain.model.Beer
import com.filipradon.domain.repository.BeersRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

class GetBeersTest {

    private lateinit var getBeers: GetBeers
    private var beersRepository: BeersRepository = mock()
    private var postExecutionThread: PostExecutionThread = mock()

    @Before
    fun setUp() {
        getBeers = GetBeers(beersRepository, postExecutionThread)
    }

    @Test
    fun `assert that getBeers completes`() {
        stubGetBeers(Observable.just(BeerDataFactory.makeBeerList(5)))
        val testObserver = getBeers.buildUserCaseObservable().test()

        testObserver.assertComplete()
    }

    @Test
    fun `assert that getBeers returns proper data`() {
        val beers = BeerDataFactory.makeBeerList(5)
        stubGetBeers(Observable.just(beers))
        val testObserver = getBeers.buildUserCaseObservable().test()

        testObserver.assertValue(beers)
    }

    private fun stubGetBeers(observable: Observable<List<Beer>>) {
        whenever(beersRepository.getBeers()).thenReturn(observable)
    }
}