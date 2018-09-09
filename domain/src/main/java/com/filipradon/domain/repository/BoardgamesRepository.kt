package com.filipradon.domain.repository

import com.filipradon.domain.model.Boardgame
import io.reactivex.Observable

interface BoardgamesRepository {

    fun getBoardgames(): Observable<List<Boardgame>>

}