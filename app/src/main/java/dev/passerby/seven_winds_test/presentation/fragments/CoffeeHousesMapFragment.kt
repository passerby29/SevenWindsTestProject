package dev.passerby.seven_winds_test.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.map.TextStyle
import com.yandex.runtime.image.ImageProvider
import dev.passerby.seven_winds_test.R
import dev.passerby.seven_winds_test.databinding.FragmentCoffeeHousesMapBinding
import dev.passerby.seven_winds_test.domain.models.CoffeeHouseItemModel
import dev.passerby.seven_winds_test.presentation.viewmodels.CoffeeHousesViewModel

class CoffeeHousesMapFragment : Fragment() {

    private var _binding: FragmentCoffeeHousesMapBinding? = null
    private val binding: FragmentCoffeeHousesMapBinding
        get() = _binding ?: throw RuntimeException("FragmentCoffeeHousesMapBinding is null")

    private val viewModel by lazy {
        ViewModelProvider(this)[CoffeeHousesViewModel::class.java]
    }

    private lateinit var placeMarkObject: PlacemarkMapObject
    private lateinit var map: Map
    private lateinit var point: Point

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.initialize(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoffeeHousesMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        map = binding.mapview.mapWindow.map

        binding.coffeeHousesToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.coffeeHouses.observe(viewLifecycleOwner) { list ->
            point = Point(list[0].point.latitude.toDouble(), list[0].point.longitude.toDouble())
            map.move(CameraPosition(point, 10f, 150f, 30f))

            list.forEach { item ->
                initMapPoint(item)
            }
        }
    }

    private fun initMapPoint(item: CoffeeHouseItemModel) {

        val imageProvider = ImageProvider.fromResource(requireContext(), R.drawable.im_coffee_point)

        point = Point(item.point.latitude.toDouble(), item.point.longitude.toDouble())

        placeMarkObject = map.mapObjects.addPlacemark().apply {
            geometry = point
            setIcon(imageProvider)
            setText(item.name,
                TextStyle().apply {
                    size = 14f
                    placement = TextStyle.Placement.BOTTOM
                    color = ContextCompat.getColor(requireContext(), R.color.text_color)
                }
            )
            addTapListener { _, _ ->
                findNavController().navigate(
                    CoffeeHousesMapFragmentDirections
                        .actionCoffeeHousesMapFragmentToMenuFragment(item.id)
                )
                true
            }
        }
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        binding.mapview.onStart()
    }

    override fun onStop() {
        binding.mapview.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}