package com.aliosmanunaldi.wusicapp.ui.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.aliosmanunaldi.wusicapp.data.home.HomeRepository
import com.aliosmanunaldi.wusicapp.data.home.models.RoomResponse
import com.aliosmanunaldi.wusicapp.databinding.FragmentHomeBinding
import com.aliosmanunaldi.wusicapp.ui.common.LinearItemDecoration
import com.aliosmanunaldi.wusicapp.ui.homepage.city.CityListAdapter
import com.aliosmanunaldi.wusicapp.ui.homepage.city.CityListViewState

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val args: HomeFragmentArgs by navArgs()

    lateinit var listAdapter: RoomListAdapter
    lateinit var cityAdapter: CityListAdapter

    val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(
            repository = HomeRepository()
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        setUpViewModel()
        viewModel.fetchCities()
    }

    private fun setUpView() {
        with(binding.roomsRecyclerView) {
            apply {
                listAdapter = RoomListAdapter()
                adapter = listAdapter.apply {
                    itemClickListener = ::navigateRoomDetailFragment
                }
                addItemDecoration(LinearItemDecoration())
            }
        }
        with(binding.citiesRecyclerView) {
            cityAdapter = CityListAdapter()
            adapter = cityAdapter.apply {
                itemClickListener = {
                    fetchRooms(it.city)
                }
            }
            addItemDecoration(LinearItemDecoration())
        }
    }

    private fun navigateRoomDetailFragment(roomResponse: RoomResponse) {

        val id: Int = roomResponse?.id ?: 0
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToRoomDetailFragment(
                id
            )
        )
    }

    private fun fetchRooms(cityName: String) {
        viewModel.fetchRoomList(args.userId, cityName)
    }

    private fun setUpViewModel() {

        with(viewModel) {
            getPageLiveData().observe(viewLifecycleOwner) {
                renderPageViewState(it)
            }
            getCitesLiveData().observe(viewLifecycleOwner) {
                renderCityListViewState(it)
            }
        }
    }

    private fun renderPageViewState(viewState: RoomListViewState) {
        binding.viewState = viewState
        listAdapter.submitList(viewState.rooms.rooms)
    }

    private fun renderCityListViewState(viewState: CityListViewState) {
        binding.cityViewState = viewState
        cityAdapter.submitList(viewState.list.cityList)
    }
}