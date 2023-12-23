package dev.passerby.seven_winds_test.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dev.passerby.seven_winds_test.R
import dev.passerby.seven_winds_test.databinding.FragmentOrderBinding
import dev.passerby.seven_winds_test.domain.models.MenuItemModel
import dev.passerby.seven_winds_test.presentation.adapters.OrderAdapter
import dev.passerby.seven_winds_test.presentation.viewmodels.MenuViewModel

class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null
    private val binding: FragmentOrderBinding
        get() = _binding ?: throw RuntimeException("FragmentOrderBinding is null")

    private val viewModel: MenuViewModel by navGraphViewModels(R.id.main_navigation)

    private lateinit var orderAdapter: OrderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        orderAdapter = OrderAdapter(requireContext())
        orderAdapter.onItemMinusCLickListener = {
            viewModel.minusCount(it)
            orderAdapter.notifyItemChanged(it)
        }

        orderAdapter.onItemPlusCLickListener = {
            viewModel.plusCount(it)
            orderAdapter.notifyItemChanged(it)
        }

        binding.orderRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = orderAdapter
        }

        viewModel.menuList.observe(viewLifecycleOwner) {
            val list = mutableListOf<MenuItemModel>()
            it.forEach { item ->
                if (item.count != 0) {
                    list.add(item)
                }
            }
            orderAdapter.submitList(list)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}