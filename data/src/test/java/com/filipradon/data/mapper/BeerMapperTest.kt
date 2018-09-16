package com.filipradon.data.mapper

import com.filipradon.data.model.BeerEntity
import com.filipradon.data.test.factory.BeerFactory
import com.filipradon.domain.model.Beer
import org.junit.Test
import kotlin.test.assertEquals

class BeerMapperTest {

    private val mapper = BeerMapper()

    @Test
    fun `assert mapping from entity returns proper data`() {
        val entity = BeerFactory.makeBeerEntity()
        val model = mapper.mapFromEntity(entity)

        assertEqualData(entity, model)
    }

    @Test
    fun `assert mapping to entity returns proper data`() {
        val model = BeerFactory.makeBeer()
        val entity = mapper.mapToEntity(model)

        assertEqualData(entity, model)
    }

    private fun assertEqualData(entity: BeerEntity,
                                model: Beer) {
        assertEquals(entity.name, model.name)
        assertEquals(entity.brewery, model.brewery)
        assertEquals(entity.style, model.style)
        assertEquals(entity.ibu, model.ibu)
        assertEquals(entity.blg, model.blg)
        assertEquals(entity.abv, model.abv)
        assertEquals(entity.prices, model.prices)

    }

}