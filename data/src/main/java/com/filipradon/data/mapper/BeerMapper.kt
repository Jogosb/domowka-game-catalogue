package com.filipradon.data.mapper

import com.filipradon.data.model.BeerEntity
import com.filipradon.domain.model.Beer
import javax.inject.Inject

open class BeerMapper @Inject constructor() : EntityMapper<BeerEntity, Beer> {

    override fun mapFromEntity(entity: BeerEntity): Beer {
        return Beer(entity.name, entity.brewery, entity.style, entity.ibu, entity.blg, entity.abv,
                entity.prices)
    }

    override fun mapToEntity(domain: Beer): BeerEntity {
        return BeerEntity(domain.name, domain.brewery, domain.style, domain.ibu, domain.blg, domain.abv,
                domain.prices)
    }
}