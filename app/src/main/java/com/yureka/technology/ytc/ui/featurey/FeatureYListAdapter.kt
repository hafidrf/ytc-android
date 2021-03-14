package com.yureka.technology.ytc.ui.featurey

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.yureka.technology.ytc.R
import com.yureka.technology.ytc.databinding.ListItemFeatureYBinding
import com.yureka.technology.ytc.ui.common.DataBoundListAdapter
import com.yureka.technology.ytc.ui.featurey.model.ItemModel

class FeatureYListAdapter(
    private val itemClickCallback: ((ItemModel) -> Unit)?
) : DataBoundListAdapter<ItemModel, ListItemFeatureYBinding>(
    diffCallback = object : DiffUtil.ItemCallback<ItemModel>() {
        override fun areItemsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun createBinding(parent: ViewGroup): ListItemFeatureYBinding {
        val binding = DataBindingUtil.inflate<ListItemFeatureYBinding>(
            LayoutInflater.from(parent.context), R.layout.list_item_feature_y,
            parent, false
        )

        binding.actionDelete.setOnClickListener {
            binding.data?.let {
                itemClickCallback?.invoke(it)
            }
        }
        return binding
    }

    override fun bind(binding: ListItemFeatureYBinding, item: ItemModel) {
        binding.data = item
    }
}
