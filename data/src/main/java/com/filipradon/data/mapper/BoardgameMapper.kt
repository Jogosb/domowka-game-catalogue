package com.filipradon.data.mapper

import com.filipradon.data.model.BoardgameEntity
import com.filipradon.domain.model.Boardgame
import javax.inject.Inject

class BoardgameMapper @Inject constructor(): EntityMapper<BoardgameEntity, Boardgame> {

    override fun mapFromEntity(entity: BoardgameEntity): Boardgame {
        return Boardgame(entity.id, entity.name, entity.imageId, entity.editDate)
    }

    override fun mapToEntity(domain: Boardgame): BoardgameEntity {
        return BoardgameEntity(domain.id, domain.name, domain.imageId, domain.editDate)
    }
}