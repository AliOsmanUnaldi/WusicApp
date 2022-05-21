package com.aliosmanunaldi.wusicapp.ui.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.aliosmanunaldi.wusicapp.R
import com.aliosmanunaldi.wusicapp.data.home.HomeRepository
import com.aliosmanunaldi.wusicapp.data.home.models.RoomResponse
import com.aliosmanunaldi.wusicapp.databinding.FragmentHomeBinding
import com.aliosmanunaldi.wusicapp.ui.city.CityListAdapter
import com.aliosmanunaldi.wusicapp.ui.city.CityListViewState
import com.aliosmanunaldi.wusicapp.ui.common.LinearItemDecoration

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
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )


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
        if (args.roomId != -1){
            binding.createRoomButton.text = "ODAM"
        }


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
        binding.createRoomButton.setOnClickListener { view ->
            if (args.roomId == -1) {
                navigateAddRoomFragment()
            } else {
                navigateMyRoomFragment()
            }

        }
    }

    private fun navigateMyRoomFragment() {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToMyRoomFragment(
                args.roomId
            )
        )
    }

    private fun navigateAddRoomFragment() {

        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToAddRoomFragment(
                args.userId
            )
        )
    }

    private fun navigateRoomDetailFragment(roomResponse: RoomResponse) {

        val roomId: Int = roomResponse?.id ?: 0
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToRoomDetailFragment(
                roomId,
                args.userId
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