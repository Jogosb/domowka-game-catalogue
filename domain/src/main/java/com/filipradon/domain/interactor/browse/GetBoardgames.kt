package com.filipradon.domain.interactor.browse

import com.filipradon.domain.executor.PostExecutionThread
import com.filipradon.domain.interactor.ObservableUserCase
import com.filipradon.domain.model.Boardgame
import com.filipradon.domain.repository.BoardgamesRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetBoardgames @Inject constructor(
        private val boardgamesRepository: BoardgamesRepository,
        postExecutionThread: PostExecutionThread)
    : ObservableUserCase<List<Boardgame>, Nothing>(postExecutionThread) {

    override fun buildUserCaseObservable(params: Nothing?): Observable<List<Boardgame>> {
        return boardgamesRepository.getBoardgames()
    }
}