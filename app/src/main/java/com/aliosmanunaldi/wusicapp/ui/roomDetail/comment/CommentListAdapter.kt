package com.aliosmanunaldi.wusicapp.ui.roomDetail.comment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aliosmanunaldi.wusicapp.data.roomDetail.comment.CommentResponse
import com.aliosmanunaldi.wusicapp.databinding.ItemCommentBinding

class CommentListAdapter :
    ListAdapter<CommentResponse, CommentListAdapter.CharacterListHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListHolder {
        val binding = ItemCommentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CharacterListHolder(binding)
    }

    class CharacterListHolder(
        private val binding: ItemCommentBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(comment: CommentResponse) {
            binding.itemViewState = CommentItemViewState(comment)
        }
    }

    override fun onBindViewHolder(holder: CharacterListHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CommentResponse>() {
            override fun areItemsTheSame(oldItem: CommentResponse, newItem: CommentResponse) =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: CommentResponse, newItem: CommentResponse) =
                oldItem == newItem
        }
    }
}