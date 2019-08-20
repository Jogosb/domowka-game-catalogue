package com.filipradon.remote

import com.filipradon.data.model.BeerEntity
import com.filipradon.data.repository.BeersRemote
import com.filipradon.remote.mapper.BeersResponseModelMapper
import com.filipradon.remote.service.DomowkaBeersService
import io.reactivex.Observable
import javax.inject.Inject

class BeersRemoteImpl @Inject constructor(
        private val service: DomowkaBeersService,
        private val mapper: BeersResponseModelMapper
): BeersRemote {

    override fun getBeers(): Observable<List<BeerEntity>> {
        return service.getBeers().map {
            it.beers.map { mapper.mapFromModel(it) }
        }
    }
}