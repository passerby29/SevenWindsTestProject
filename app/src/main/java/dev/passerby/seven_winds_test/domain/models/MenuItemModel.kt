package dev.passerby.seven_winds_test.domain.models

data class MenuItemModel(
    val id: Int,
    val imageURL: String,
    val name: String,
    val price: Int,
    var count: Int = 0
) {
    fun plusCount() {
        count++
    }

    fun minusCount() {
        if (count != 0) {
            count--
        }
    }
}