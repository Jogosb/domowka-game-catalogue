package com.filipradon.data

import com.filipradon.data.mapper.BeerMapper
import com.filipradon.data.repository.BeersCache
import com.filipradon.data.store.BeersDataStoreFactory
import com.filipradon.domain.model.Beer
import com.filipradon.domain.repository.BeersRepository
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class BeersDataRepository @Inject constructor(
        private val mapper: BeerMapper,
        private val cache: BeersCache,
        private val factory: BeersDataStoreFactory) : BeersRepository {

    override fun getBeers(): Observable<List<Beer>> {
        return Observable.zip(cache.areBeersCached().toObservable(),
                cache.isBeersCacheExpired().toObservable()
                , BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { areCached, isExpired -> areCached to isExpired })
                .flatMap {
                    factory.getDataStore(it.first, it.second).getBeers()
                }
                .flatMap { beers ->
                    factory.getCacheDataStore()
                            .saveBeers(beers)
                            .andThen(Observable.just(beers))
                }
                .map { it.map { mapper.mapFromEntity(it) } }

    }
}