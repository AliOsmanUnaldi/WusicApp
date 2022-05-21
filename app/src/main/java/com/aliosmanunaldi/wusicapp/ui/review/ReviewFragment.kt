package com.aliosmanunaldi.wusicapp.ui.review

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.aliosmanunaldi.wusicapp.Comment
import com.aliosmanunaldi.wusicapp.Point
import com.aliosmanunaldi.wusicapp.Review
import com.aliosmanunaldi.wusicapp.data.review.ReviewRepository
import com.aliosmanunaldi.wusicapp.databinding.FragmentReviewBinding
import com.google.android.material.snackbar.Snackbar

class ReviewFragment : Fragment() {

    private var _binding: FragmentReviewBinding? = null
    private val binding get() = _binding!!
    private val args: com.aliosmanunaldi.wusicapp.ui.review.ReviewFragmentArgs by navArgs()

    val viewModel: ReviewViewModel by viewModels {
        ReviewViewModelFactory(
            repository = ReviewRepository()
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
        _binding = FragmentReviewBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.submitPointAndCommentButton.setOnClickListener {
            val point = binding.givePointEditText.text.toString()
            val comment = binding.commentEditText.text.toString()

            if (point == "" || comment == "") {
                Snackbar.make(
                    binding.linearLayout,
                    "Lütfen yorum yapıp puan veriniz!",
                    Snackbar.LENGTH_LONG
                ).show()
            } else {
                val review = Review(
                    point = Point(args.userId, args.roomOwnerId, point.toDouble()),
                    comment = Comment(args.userId, args.roomOwnerId, comment)
                )
                viewModel.setReview(review)
            }

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

    private fun renderPageViewState(viewState: ReviewPageViewState) {

        findNavController().navigate(
            ReviewFragmentDirections.actionReviewToHomeFragment(
                args.userId,
                -1
            )
        )

        Snackbar.make(
            binding.linearLayout,
            viewState.result?.message.toString(),
            Snackbar.LENGTH_LONG
        ).show()
    }
}