package com.aliosmanunaldi.wusicapp.ui.homepage.roomDetail

import androidx.fragment.app.Fragment
import com.aliosmanunaldi.wusicapp.databinding.FragmentRoomDetailBinding

class RoomDetailFragment : Fragment() {

    private var _binding: FragmentRoomDetailBinding? = null
    private val binding get() = _binding!!

    /*
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

     */

}