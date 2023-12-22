package dev.passerby.seven_winds_test.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import dev.passerby.seven_winds_test.databinding.FragmentMenuBinding
import dev.passerby.seven_winds_test.presentation.adapters.MenuAdapter
import dev.passerby.seven_winds_test.presentation.factories.MenuViewModelFactory
import dev.passerby.seven_winds_test.presentation.viewmodels.MenuViewModel

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding: FragmentMenuBinding
        get() = _binding ?: throw RuntimeException("FragmentMenuBinding is null")

    private val args by navArgs<MenuFragmentArgs>()

    private val viewModelFactory by lazy {
        MenuViewModelFactory(requireActivity().application, args.id)
    }
    private val menuViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MenuViewModel::class.java]
    }
    private lateinit var menuAdapter: MenuAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menuAdapter = MenuAdapter(requireContext())
        menuAdapter.onItemMinusCLickListener = {
            menuViewModel.minusCount(it)
            menuAdapter.notifyItemChanged(it)
        }
        menuAdapter.onItemPlusCLickListener = {
            menuViewModel.plusCount(it)
            menuAdapter.notifyItemChanged(it)
        }
        binding.menuRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = menuAdapter
        }
        menuViewModel.menuList.observe(viewLifecycleOwner) {
            menuAdapter.submitList(it)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}