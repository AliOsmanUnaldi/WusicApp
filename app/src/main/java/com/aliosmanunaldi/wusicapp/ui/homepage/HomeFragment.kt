package com.aliosmanunaldi.wusicapp.ui.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.aliosmanunaldi.wusicapp.data.home.HomeRepository
import com.aliosmanunaldi.wusicapp.databinding.FragmentHomeBinding
import com.aliosmanunaldi.wusicapp.ui.common.LinearItemDecoration

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    lateinit var listAdapter: RoomListAdapter

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
        viewModel.fetchRoomList(3, "ANKARA")
        setUpViewModel()
    }

    private fun setUpView() {
        with(binding.roomsRecyclerView) {
            apply {
                listAdapter = RoomListAdapter()
                adapter = listAdapter.apply {
                    // itemClickListener = ::navigateRoomDetailFragment
                }
                addItemDecoration(LinearItemDecoration())
            }
        }
    }

    private fun setUpViewModel() {

        with(viewModel) {
            getPageLiveData().observe(viewLifecycleOwner) {
                renderPageViewState(it)
            }
        }
    }

    private fun renderPageViewState(viewState: RoomListViewState) {
        binding.viewState = viewState
        listAdapter.submitList(viewState.rooms.rooms)
    }
}