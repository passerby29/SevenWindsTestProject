package dev.passerby.seven_winds_test.presentation.callbacks

import androidx.recyclerview.widget.DiffUtil
import dev.passerby.seven_winds_test.domain.models.MenuItemModel

class MenuItemDiffCallback: DiffUtil.ItemCallback<MenuItemModel>() {
    override fun areItemsTheSame(
        oldItem: MenuItemModel,
        newItem: MenuItemModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: MenuItemModel,
        newItem: MenuItemModel
    ): Boolean {
        return oldItem == newItem
    }
}