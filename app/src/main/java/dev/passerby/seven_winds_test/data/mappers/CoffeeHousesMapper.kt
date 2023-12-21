package dev.passerby.seven_winds_test.data.mappers

import dev.passerby.seven_winds_test.data.models.db.CoffeeHouseItemDbModel
import dev.passerby.seven_winds_test.data.models.db.CoffeeHousesListDbModel
import dev.passerby.seven_winds_test.data.models.db.PointDbModel
import dev.passerby.seven_winds_test.data.models.dto.CoffeeHouseItemDto
import dev.passerby.seven_winds_test.data.models.dto.PointDto
import dev.passerby.seven_winds_test.domain.models.CoffeeHouseItemModel
import dev.passerby.seven_winds_test.domain.models.CoffeeHousesListModel
import dev.passerby.seven_winds_test.domain.models.PointModel

class CoffeeHousesMapper {

    fun mapDtoItemToDbModelItem(dtoItem: CoffeeHouseItemDto) = CoffeeHouseItemDbModel(
        id = dtoItem.id,
        name = dtoItem.name,
        point = mapDtoPointToDbModelPoint(dtoItem.point),
    )

    private fun mapDtoPointToDbModelPoint(dtoPoint: PointDto) = PointDbModel(
        latitude = dtoPoint.latitude,
        longitude = dtoPoint.longitude
    )

    fun mapDbModelToEntity(dbModel: CoffeeHousesListDbModel): CoffeeHousesListModel {
        val coffeeHousesList = dbModel.map {
            mapDbModelItemToEntityItem(it)
        }
        return coffeeHousesList as CoffeeHousesListModel
    }

    private fun mapDbModelItemToEntityItem(dbModelItem: CoffeeHouseItemDbModel) =
        CoffeeHouseItemModel(
            id = dbModelItem.id,
            name = dbModelItem.name,
            point = mapDbModelPointToEntityPoint(dbModelItem.point),
        )

    private fun mapDbModelPointToEntityPoint(dbModelPoint: PointDbModel) = PointModel(
        latitude = dbModelPoint.latitude,
        longitude = dbModelPoint.longitude
    )
}