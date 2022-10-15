package com.timife.makeup.presentation.makeupBrands

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.timife.makeup.databinding.MakeupListItemBinding
import com.timife.domain.model.MakeupItem

class MakeupItemsAdapter(private val clickListener: BrandClickListener) :
    ListAdapter<MakeupItem, MakeupItemsAdapter.MakeupViewHolder>(MakeupDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MakeupViewHolder {
        return MakeupViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MakeupViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            if (item != null) {
                clickListener.onClick(item)
            }
        }
    }

    class MakeupViewHolder(private val binding: MakeupListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MakeupItem) {
            binding.brandItem = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MakeupViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MakeupListItemBinding.inflate(layoutInflater, parent, false)
                return MakeupViewHolder(binding)

            }
        }
    }

    class MakeupDiffUtil : DiffUtil.ItemCallback<MakeupItem>() {
        override fun areItemsTheSame(oldItem: MakeupItem, newItem: MakeupItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MakeupItem, newItem: MakeupItem): Boolean {
            return oldItem.brand == newItem.brand
        }
    }

    class BrandClickListener(val clickListener:(item: MakeupItem) -> Unit){
        fun onClick(item: MakeupItem){
            clickListener(item)
        }
    }
}