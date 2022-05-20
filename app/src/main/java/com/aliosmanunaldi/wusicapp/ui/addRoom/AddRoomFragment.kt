package com.aliosmanunaldi.wusicapp.ui.addRoom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.aliosmanunaldi.wusicapp.data.addRoom.AddRoomRepository
import com.aliosmanunaldi.wusicapp.data.addRoom.AddRoomRequest
import com.aliosmanunaldi.wusicapp.databinding.FragmentAddRoomBinding
import com.google.android.material.snackbar.Snackbar

class AddRoomFragment : Fragment() {

    private var _binding: FragmentAddRoomBinding? = null
    private val binding get() = _binding!!
    private val args: AddRoomFragmentArgs by navArgs()

    val viewModel: AddRoomViewModel by viewModels {
        AddRoomViewModelFactory(
            repository = AddRoomRepository()
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
        _binding = FragmentAddRoomBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.deleteRoom.visibility = View.GONE

        binding.addRoomButton.setOnClickListener {
            val addRoomRequest = AddRoomRequest(
                ownerId = args.userId,
                roomName = binding.roomNameEditText.text.toString(),
                genre = binding.roomGenreEditText.text.toString(),
                cityId = 1,
                description = binding.roomDescriptionEditText.text.toString(),
                password = null
            )
            viewModel.setRoom(addRoomRequest)
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

    private fun renderPageViewState(viewState: AddRoomPageViewState) {

        binding.viewState = viewState
        binding.deleteRoom.visibility = View.VISIBLE

        Snackbar.make(
            binding.linearLayout,
            viewState.result?.message.toString(),
            Snackbar.LENGTH_LONG
        ).show()
    }
}