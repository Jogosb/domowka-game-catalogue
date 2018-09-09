package com.filipradon.domain.repository

import com.filipradon.domain.model.Beer
import io.reactivex.Observable

interface BeersRepository {

    fun getBeers(): Observable<List<Beer>>

}