package com.filipradon.data.test.factory

import com.filipradon.data.model.BoardgameEntity
import com.filipradon.domain.model.Boardgame

object BoardgameFactory {

    fun makeBoardgameEntity(): BoardgameEntity {
        return BoardgameEntity(DataFactory.randomString(), DataFactory.randomString(),
                DataFactory.randomString(), DataFactory.randomString())
    }

    fun makeBoardgame(): Boardgame {
        return Boardgame(DataFactory.randomString(), DataFactory.randomString(),
                DataFactory.randomString(), DataFactory.randomString())
    }

}