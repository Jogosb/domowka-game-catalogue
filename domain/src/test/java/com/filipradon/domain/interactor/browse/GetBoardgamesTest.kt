package com.filipradon.domain.interactor.browse

import com.filipradon.domain.executor.PostExecutionThread
import com.filipradon.domain.interactor.test.BoardgameDataFactory
import com.filipradon.domain.model.Boardgame
import com.filipradon.domain.repository.BoardgamesRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

class GetBoardgamesTest {

    private lateinit var getBoardgames: GetBoardgames
    private var boardgamesRepository: BoardgamesRepository = mock()
    private var postExecutionThread: PostExecutionThread = mock()

    @Before
    fun setUp() {
        getBoardgames = GetBoardgames(boardgamesRepository, postExecutionThread)
    }

    @Test
    fun `assert that getBoardgames completes`() {
        stubGetBoardgames(Observable.just(BoardgameDataFactory.makeBoardgameList(5)))
        val testObserver = getBoardgames.buildUseCaseObservable().test()

        testObserver.assertComplete()
    }

    @Test
    fun `assert that getBoardgames returns proper data`() {
        val boardgames = BoardgameDataFactory.makeBoardgameList(5)
        stubGetBoardgames(Observable.just(boardgames))
        val testObserver = getBoardgames.buildUseCaseObservable().test()

        testObserver.assertValue(boardgames)
    }

    private fun stubGetBoardgames(observable: Observable<List<Boardgame>>) {
        whenever(boardgamesRepository.getBoardgames()).thenReturn(observable)
    }

}