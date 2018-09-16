package com.filipradon.data

import com.filipradon.data.mapper.BoardgameMapper
import com.filipradon.data.repository.BoardgamesCache
import com.filipradon.data.store.BoardgamesDataStoreFactory
import com.filipradon.domain.model.Boardgame
import com.filipradon.domain.repository.BoardgamesRepository
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class BoardgamesDataRepository @Inject constructor(
        private val mapper: BoardgameMapper,
        private val cache: BoardgamesCache,
        private val factory: BoardgamesDataStoreFactory
) : BoardgamesRepository {

    override fun getBoardgames(): Observable<List<Boardgame>> {
        return Observable.zip(cache.areBoardgamesCached().toObservable(),
                cache.isBoardgamesCacheExpired().toObservable(),
                BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { areCached, isExpired ->
                    areCached to isExpired
                })
                .flatMap {
                    factory.getDataStore(it.first, it.second).getBoardgames()
                }
                .flatMap { boardgames ->
                    factory.getCacheDataStore()
                            .saveBoardgames(boardgames)
                            .andThen(Observable.just(boardgames))
                }
                .map { it.map { mapper.mapFromEntity(it) } }
    }
}