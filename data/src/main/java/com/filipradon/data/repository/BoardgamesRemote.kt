package com.filipradon.data.repository

import com.filipradon.data.model.BoardgameEntity
import io.reactivex.Observable

interface BoardgamesRemote {

    fun getBoardgames(): Observable<List<BoardgameEntity>>
}