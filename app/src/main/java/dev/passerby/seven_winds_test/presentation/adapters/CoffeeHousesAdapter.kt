package dev.passerby.seven_winds_test.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import dev.passerby.seven_winds_test.databinding.ItemCoffeeHouseLayoutBinding
import dev.passerby.seven_winds_test.domain.models.CoffeeHouseItemModel
import dev.passerby.seven_winds_test.presentation.callbacks.CoffeeHouseDiffCallback
import dev.passerby.seven_winds_test.presentation.viewholders.CoffeeHousesViewHolder

class CoffeeHousesAdapter :
    ListAdapter<CoffeeHouseItemModel, CoffeeHousesViewHolder>(CoffeeHouseDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeHousesViewHolder {
        val itemView = ItemCoffeeHouseLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CoffeeHousesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CoffeeHousesViewHolder, position: Int) {
        val item = getItem(position)
        val binding = holder.binding
        with(binding) {
            coffeeHouseNameTextView.text = item.name
        }
    }
}