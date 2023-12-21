package dev.passerby.seven_winds_test.data.mappers

import dev.passerby.seven_winds_test.data.models.dto.AuthDataDto
import dev.passerby.seven_winds_test.data.models.dto.AuthUserDataDto
import dev.passerby.seven_winds_test.domain.models.AuthDataModel
import dev.passerby.seven_winds_test.domain.models.AuthUserDataModel

class AuthMapper {

    fun mapResponseDtoToEntity(dto: AuthDataDto) = AuthDataModel(
        token = dto.token,
        tokenLifetime = dto.tokenLifetime,
    )

    fun mapUserEntityToDto(entity: AuthUserDataModel) = AuthUserDataDto(
        login = entity.login,
        password = entity.password
    )
}