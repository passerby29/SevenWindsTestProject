package dev.passerby.seven_winds_test.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import dev.passerby.seven_winds_test.R
import dev.passerby.seven_winds_test.databinding.ItemOrderLayoutBinding
import dev.passerby.seven_winds_test.domain.models.MenuItemModel
import dev.passerby.seven_winds_test.presentation.callbacks.MenuItemDiffCallback
import dev.passerby.seven_winds_test.presentation.viewholders.OrderViewHolder

class OrderAdapter(private val context: Context) :
    ListAdapter<MenuItemModel, OrderViewHolder>(MenuItemDiffCallback()) {

    var onItemMinusCLickListener: ((Int) -> Unit)? = null
    var onItemPlusCLickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val itemView = ItemOrderLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return OrderViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val item = getItem(position)
        val binding = holder.binding
        val price = item.price * item.count
        with(binding) {
            orderItemNameTextView.text = item.name
            orderItemPriceTextView.text = context.getString(R.string.price_placeholder, price.toString())
            orderItemCountTextView.text = item.count.toString()
            orderItemMinusButton.setOnClickListener { onItemMinusCLickListener?.invoke(position) }
            orderItemPlusButton.setOnClickListener { onItemPlusCLickListener?.invoke(position) }
        }
    }
}