package com.filipradon.data.test.factory

import com.filipradon.data.model.BeerEntity
import com.filipradon.domain.model.Beer

object BeerFactory {

    fun makeBeerEntity(): BeerEntity {
        return BeerEntity(DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(),
                DataFactory.randomString(), DataFactory.randomString(),
                DataFactory.randomString(), listOf(DataFactory.randomString(), DataFactory.randomString()))
    }

    fun makeBeer(): Beer {
        return Beer(DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(),
                DataFactory.randomString(), DataFactory.randomString(),
                DataFactory.randomString(), listOf(DataFactory.randomString(), DataFactory.randomString()))
    }

}