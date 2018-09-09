package com.filipradon.domain.interactor.test

import com.filipradon.domain.model.Boardgame
import java.util.UUID

object BoardgameDataFactory {

    fun randomUuid(): String = UUID.randomUUID().toString()

    fun makeBoardgame(): Boardgame {
        return Boardgame(randomUuid(), randomUuid(), randomUuid(), randomUuid())
    }

    fun makeBoardgameList(count: Int): List<Boardgame> {
        val boardgames = mutableListOf<Boardgame>()
        repeat(count) {
            boardgames.add(makeBoardgame())
        }
        return boardgames
    }

}