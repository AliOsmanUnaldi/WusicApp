package com.aliosmanunaldi.wusicapp.ui.myroom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.aliosmanunaldi.wusicapp.R
import com.aliosmanunaldi.wusicapp.data.myroom.MyRoomRepository
import com.aliosmanunaldi.wusicapp.data.myroom.MyRoomResponse
import com.aliosmanunaldi.wusicapp.databinding.FragmentMyRoomBinding
import com.aliosmanunaldi.wusicapp.ui.common.LinearItemDecoration
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

class MyRoomFragment : Fragment() {

    private var _binding: FragmentMyRoomBinding? = null
    private val binding get() = _binding!!
    private val args: MyRoomFragmentArgs by navArgs()
    lateinit var participantListAdapter: ParticipantListAdapter


    val viewModel: MyRoomViewModel by viewModels {
        MyRoomViewModelFactory(
            repository = MyRoomRepository()
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
        _binding = FragmentMyRoomBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        setUpViewModel()
        viewModel.getParticipantList(args.roomId)
    }

    private fun setUpViewModel() {

        with(viewModel) {
            getParticipantListLiveData().observe(viewLifecycleOwner) {
                renderParticipants(it)
            }
            getRemoveRoomLiveData().observe(viewLifecycleOwner) {
                renderRemoveMyRoom(it)
            }
        }
    }

    private fun renderRemoveMyRoom(it: MyRoomResponse?) {
        if (it?.success == true) {
            navigateHomeFragment(it)

        } else {
            Snackbar.make(
                binding.root,
                it?.message.toString(),
                Snackbar.LENGTH_LONG
            ).show()
        }

    }

    private fun navigateHomeFragment(response: MyRoomResponse) {
        findNavController().navigate(
            MyRoomFragmentDirections.actionMyRoomFragmentToHomeFragment(
                args.userId,
                -1
            )
        )
        Snackbar.make(
            binding.root,
            response.message.toString(),
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun setUpView() {
        with(binding.participantsRecyclerView) {
            apply {
                participantListAdapter = ParticipantListAdapter()
                adapter = participantListAdapter
                addItemDecoration(LinearItemDecoration(spacing = R.dimen.item_participants_spacing))
            }
        }
        binding.deleteRoom.setOnClickListener {
            viewModel.removeMyRoom(args.roomId)
        }
    }

    private fun renderParticipants(list: List<String>) {
        val myList: List<Participant> = list.map { Participant(it) }
        if (myList.isEmpty()) {
            binding.participantsTextView.text = "Katılımcı henüz yok :("
            binding.participantsRecyclerView.visibility = View.GONE
        }
        participantListAdapter.submitList(myList)
        participantListAdapter.notifyDataSetChanged()
        Glide.with(this)
            .load(R.drawable.suprise)
            .into(binding.roomActiveGif);
    }

}