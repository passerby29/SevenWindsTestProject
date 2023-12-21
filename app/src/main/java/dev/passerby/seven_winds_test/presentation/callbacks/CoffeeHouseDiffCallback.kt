package dev.passerby.seven_winds_test.presentation.callbacks

import androidx.recyclerview.widget.DiffUtil
import dev.passerby.seven_winds_test.domain.models.CoffeeHouseItemModel

class CoffeeHouseDiffCallback: DiffUtil.ItemCallback<CoffeeHouseItemModel>() {
    override fun areItemsTheSame(
        oldItem: CoffeeHouseItemModel,
        newItem: CoffeeHouseItemModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: CoffeeHouseItemModel,
        newItem: CoffeeHouseItemModel
    ): Boolean {
        return oldItem == newItem
    }
}