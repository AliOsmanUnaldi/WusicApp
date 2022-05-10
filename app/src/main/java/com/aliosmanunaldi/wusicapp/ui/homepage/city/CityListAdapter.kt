package com.aliosmanunaldi.wusicapp.ui.homepage.city

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aliosmanunaldi.wusicapp.data.home.models.CityResponse
import com.aliosmanunaldi.wusicapp.databinding.ItemCityBinding

class CityListAdapter :
    ListAdapter<CityResponse, CityListAdapter.CharacterListHolder>(DIFF_CALLBACK) {

    var itemClickListener: (CityResponse) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListHolder {
        val binding = ItemCityBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CharacterListHolder(binding, itemClickListener)
    }

    class CharacterListHolder(
        private val binding: ItemCityBinding,
        private val itemClickListener: (CityResponse) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(city: CityResponse) {
            binding.itemViewState = CityItemViewState(city)

            binding.root.setOnClickListener {
                itemClickListener(city)
            }
        }
    }

    override fun onBindViewHolder(holder: CharacterListHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CityResponse>() {
            override fun areItemsTheSame(oldItem: CityResponse, newItem: CityResponse) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: CityResponse, newItem: CityResponse) =
                oldItem == newItem
        }
    }
}