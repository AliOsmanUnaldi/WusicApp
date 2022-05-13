package com.aliosmanunaldi.wusicapp.ui.homepage.roomDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.aliosmanunaldi.wusicapp.data.roomDetail.RoomDetailRepository
import com.aliosmanunaldi.wusicapp.databinding.FragmentRoomDetailBinding

class RoomDetailFragment : Fragment() {

    private var _binding: FragmentRoomDetailBinding? = null
    private val binding get() = _binding!!
    private val args: RoomDetailFragmentArgs by navArgs()


    val viewModel: RoomDetailViewModel by viewModels {
        RoomDetailViewModelFactory(
            repository = RoomDetailRepository()
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
        _binding = FragmentRoomDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchRoomDetail(args.roomId)
        binding.joinRoomButton.setOnClickListener {
            viewModel.setUserLogin(args.userId, args.roomId)
        }
        setUpViewModel()
    }


    private fun setUpViewModel() {

        with(viewModel) {
            getPageLiveData().observe(viewLifecycleOwner) {
                renderPageViewState(it)
            }
        }
    }

    private fun renderPageViewState(viewState: RoomDetailPageViewState) {
        binding.viewState = viewState
    }


}