package com.filipradon.domain.interactor.browse

import com.filipradon.domain.executor.PostExecutionThread
import com.filipradon.domain.interactor.ObservableUseCase
import com.filipradon.domain.model.Beer
import com.filipradon.domain.repository.BeersRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetBeers @Inject constructor(
        private val beersRepository: BeersRepository,
        postExecutionThread: PostExecutionThread)
    : ObservableUseCase<List<Beer>, Nothing>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Nothing?): Observable<List<Beer>> {
        return beersRepository.getBeers()
    }
}