package dev.passerby.seven_winds_test.data.mappers

import dev.passerby.seven_winds_test.data.models.dto.MenuItemDto
import dev.passerby.seven_winds_test.domain.models.MenuItemModel

class MenuMapper {

    fun mapDtoItemToEntityItem(dtoItem: MenuItemDto) = MenuItemModel(
        id = dtoItem.id,
        imageURL = dtoItem.imageURL,
        name = dtoItem.name,
        price = dtoItem.price
    )
}