package com.filipradon.remote.mapper

import com.filipradon.data.model.BeerEntity
import com.filipradon.remote.model.BeerModel

class BeersResponseModelMapper: ModelMapper<BeerModel, BeerEntity> {

    // TODO add valid mapping
    override fun mapFromModel(model: BeerModel): BeerEntity {
        return BeerEntity(
                name = "NAME HARDCODED!",
                abv = "ABV HARDCODED",
                blg = "BLG HARDCODED",
                brewery = "BREWERY HARDCODED",
                ibu = "IBU",
                style = "STYLE",
                prices = emptyList()
        )
    }
}