package com.filipradon.domain.interactor.test

import com.filipradon.domain.model.Beer
import java.util.UUID

object BeerDataFactory {

    fun randomUuid(): String = UUID.randomUUID().toString()

    fun makeBeer(): Beer {
        return Beer(randomUuid(), randomUuid(), randomUuid(), randomUuid(), randomUuid(),
                randomUuid(), listOf(randomUuid(), randomUuid()))
    }

    fun makeBeerList(count: Int): List<Beer> {
        val beers = mutableListOf<Beer>()
        repeat(count) {
            beers.add(makeBeer())
        }
        return beers
    }

}