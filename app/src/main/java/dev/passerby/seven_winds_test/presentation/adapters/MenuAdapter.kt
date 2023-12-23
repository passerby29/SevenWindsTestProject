package dev.passerby.seven_winds_test.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import dev.passerby.seven_winds_test.R
import dev.passerby.seven_winds_test.databinding.ItemMenuLayoutBinding
import dev.passerby.seven_winds_test.domain.models.MenuItemModel
import dev.passerby.seven_winds_test.presentation.callbacks.MenuItemDiffCallback
import dev.passerby.seven_winds_test.presentation.viewholders.MenuViewHolder

class MenuAdapter(private val context: Context) :
    ListAdapter<MenuItemModel, MenuViewHolder>(MenuItemDiffCallback()) {

    var onItemMinusCLickListener: ((Int) -> Unit)? = null
    var onItemPlusCLickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val itemView = ItemMenuLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MenuViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val item = getItem(position)
        val binding = holder.binding
        with(binding) {
            Glide.with(context).load(item.imageURL).into(menuItemMainImageView)
            menuItemNameTextView.text = item.name
            menuItemPriceTextView.text =
                context.getString(R.string.price_placeholder, item.price.toString())
            menuItemCountTextView.text = item.count.toString()
            menuItemMinusButton.setOnClickListener { onItemMinusCLickListener?.invoke(position) }
            menuItemPlusButton.setOnClickListener { onItemPlusCLickListener?.invoke(position) }
        }
    }
}