package com.aliosmanunaldi.wusicapp.ui.roomDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.aliosmanunaldi.wusicapp.R
import com.aliosmanunaldi.wusicapp.data.roomDetail.RoomDetailRepository
import com.aliosmanunaldi.wusicapp.databinding.FragmentRoomDetailBinding
import com.aliosmanunaldi.wusicapp.ui.city.CityListViewState
import com.aliosmanunaldi.wusicapp.ui.common.LinearItemDecoration
import com.aliosmanunaldi.wusicapp.ui.roomDetail.comment.CommentListAdapter
import com.aliosmanunaldi.wusicapp.ui.roomDetail.comment.CommentsViewState
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

class RoomDetailFragment : Fragment() {

    private var _binding: FragmentRoomDetailBinding? = null
    private val binding get() = _binding!!
    private val args: RoomDetailFragmentArgs by navArgs()

    lateinit var commentListAdapter: CommentListAdapter

    val viewModel: RoomDetailViewModel by viewModels {
        RoomDetailViewModelFactory(
            repository = RoomDetailRepository()
        )
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
            viewModel.setUserJoinRoom(args.userId, args.roomId)
        }
        binding.quitRoomButton.setOnClickListener {
            findNavController().navigate(
                RoomDetailFragmentDirections.actionRoomDetailToReviewFragment(
                    args.roomId,
                    args.userId,
                    binding.viewState?.getRoomOwnerId() ?: -1
                )
            )
        }
        binding.getCommentsButton.setOnClickListener {
            binding.viewState?.getRoomOwnerId()?.let {
                viewModel.fetchComments(it)
            }
        }
        setUpView()
        setUpViewModel()
    }

    private fun setUpView() {
        with(binding.commentsRecyclerView) {
            commentListAdapter = CommentListAdapter()
            adapter = commentListAdapter
            addItemDecoration(LinearItemDecoration())
        }
    }


    private fun setUpViewModel() {

        with(viewModel) {
            getPageLiveData().observe(viewLifecycleOwner) {
                renderPageViewState(it)
            }
            getJoinRoomLiveData().observe(viewLifecycleOwner) {
                renderJoinRoomViewState(it)
            }
            getQuitRoomLiveData().observe(viewLifecycleOwner) {
                renderQuitRoomViewState(it)
            }
            getCommentsLiveData().observe(viewLifecycleOwner) {
                renderCommentsViewState(it)
            }
        }
    }

    private fun renderPageViewState(viewState: RoomDetailPageViewState) {
        binding.viewState = viewState
    }

    private fun renderCommentsViewState(viewState: CommentsViewState) {
        binding.commentsViewState = viewState
        val comments = viewState.list.data.orEmpty()
        if (comments.isNotEmpty()) {
            commentListAdapter.submitList(comments)
            binding.commentsRecyclerView.visibility = View.VISIBLE
            binding.getCommentsButton.visibility = View.GONE
        } else {
            Snackbar.make(
                binding.linearLayout,
                "Odaya henüz yorum yapılmadı",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    private fun renderJoinRoomViewState(viewState: JoinRoomViewState) {
        binding.joinViewState = viewState
        Glide.with(this)
            .load(R.drawable.musicanimate)
            .into(binding.imageIcon)
        binding.quitRoomButton.visibility = viewState.isUserNotJoined()
        Snackbar.make(
            binding.linearLayout,
            viewState.response.message,
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun renderQuitRoomViewState(viewState: LeaveRoomViewState) {
        Snackbar.make(
            binding.linearLayout,
            viewState.response.message,
            Snackbar.LENGTH_SHORT
        ).show()
    }

}