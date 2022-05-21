package com.aliosmanunaldi.wusicapp.ui.myroom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aliosmanunaldi.wusicapp.R
import com.aliosmanunaldi.wusicapp.databinding.ItemParticipantBinding
import com.bumptech.glide.Glide

class ParticipantListAdapter :
    ListAdapter<Participant, ParticipantListAdapter.ParticipantListHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParticipantListHolder {
        val binding = ItemParticipantBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ParticipantListHolder(binding)
    }

    class ParticipantListHolder(
        private val binding: ItemParticipantBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(participant: Participant) {
            binding.name = participant.userName
            Glide.with(binding.participantsIcon.context).load(R.drawable.musicanimate)
                .into(binding.participantsIcon);

        }
    }

    override fun onBindViewHolder(holder: ParticipantListHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Participant>() {
            override fun areItemsTheSame(oldItem: Participant, newItem: Participant) =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Participant, newItem: Participant) =
                oldItem == newItem
        }
    }
}