package com.filipradon.data.mapper

import com.filipradon.data.model.BoardgameEntity
import com.filipradon.data.test.factory.BoardgameFactory
import com.filipradon.domain.model.Boardgame
import org.junit.Test
import kotlin.test.assertEquals

class BoardgameMapperTest {

    private val mapper = BoardgameMapper()

    @Test
    fun `assert mapping from entity returns proper data`() {
        val entity = BoardgameFactory.makeBoardgameEntity()
        val model = mapper.mapFromEntity(entity)

        assertEqualData(entity, model)
    }

    @Test
    fun `assert mapping to entity returns proper data`() {
        val model = BoardgameFactory.makeBoardgame()
        val entity = mapper.mapToEntity(model)

        assertEqualData(entity, model)
    }

    private fun assertEqualData(entity: BoardgameEntity,
                                model: Boardgame) {
        assertEquals(entity.id, model.id)
        assertEquals(entity.name, model.name)
        assertEquals(entity.imageId, model.imageId)
        assertEquals(entity.editDate, model.editDate)
    }

}