package dev.passerby.seven_winds_test.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dev.passerby.seven_winds_test.databinding.FragmentCoffeeHousesBinding
import dev.passerby.seven_winds_test.presentation.adapters.CoffeeHousesAdapter
import dev.passerby.seven_winds_test.presentation.viewmodels.CoffeeHousesViewModel

class CoffeeHousesFragment : Fragment() {

    private var _binding: FragmentCoffeeHousesBinding? = null
    private val binding: FragmentCoffeeHousesBinding
        get() = _binding ?: throw RuntimeException("FragmentCoffeeHousesBinding is null")

    private val viewModel by lazy {
        ViewModelProvider(this)[CoffeeHousesViewModel::class.java]
    }

    private var coffeeHousesAdapter = CoffeeHousesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoffeeHousesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        coffeeHousesAdapter.onCoffeeHouseItemCLickListener = {
            findNavController().navigate(
                CoffeeHousesFragmentDirections.actionCoffeeHousesFragmentToMenuFragment(
                    it
                )
            )
        }

        binding.coffeeHousesMainRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = coffeeHousesAdapter
        }

        binding.coffeeHousesToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.coffeeHouses.observe(viewLifecycleOwner) {
            coffeeHousesAdapter.submitList(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}