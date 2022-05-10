package com.aliosmanunaldi.wusicapp.ui.homepage


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aliosmanunaldi.wusicapp.data.home.models.RoomResponse
import com.aliosmanunaldi.wusicapp.databinding.ItemRoomBinding

class RoomListAdapter :
    ListAdapter<RoomResponse, RoomListAdapter.CharacterListHolder>(DIFF_CALLBACK) {

    var itemClickListener: (RoomResponse) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListHolder {
        val binding = ItemRoomBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CharacterListHolder(binding, itemClickListener)
    }

    class CharacterListHolder(
        private val binding: ItemRoomBinding,
        private val itemClickListener: (RoomResponse) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(room: RoomResponse) {
            binding.itemViewState = RoomItemViewState(room)

            binding.root.setOnClickListener {
                itemClickListener(room)
            }
        }
    }

    override fun onBindViewHolder(holder: CharacterListHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RoomResponse>() {
            override fun areItemsTheSame(oldItem: RoomResponse, newItem: RoomResponse) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: RoomResponse, newItem: RoomResponse) =
                oldItem == newItem
        }
    }
}