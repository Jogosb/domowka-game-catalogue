package com.filipradon.domain.interactor.browse

import com.filipradon.domain.executor.PostExecutionThread
import com.filipradon.domain.interactor.ObservableUserCase
import com.filipradon.domain.model.Beer
import com.filipradon.domain.repository.BeersRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetBeers @Inject constructor(
        private val beersRepository: BeersRepository,
        postExecutionThread: PostExecutionThread)
    : ObservableUserCase<List<Beer>, Nothing>(postExecutionThread) {

    override fun buildUserCaseObservable(params: Nothing?): Observable<List<Beer>> {
        return beersRepository.getBeers()
    }
}