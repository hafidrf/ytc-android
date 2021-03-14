package com.yureka.technology.ytc.ui.peringkat

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.yureka.technology.ytc.R
import com.yureka.technology.ytc.databinding.ListItemPeringkatBinding
import com.yureka.technology.ytc.ui.common.DataBoundListAdapter
import com.yureka.technology.ytc.ui.peringkat.model.ItemModel

class PeringkatAdapter(
    private val itemClickCallback: ((ItemModel) -> Unit)?
) : DataBoundListAdapter<ItemModel, ListItemPeringkatBinding>(
    diffCallback = object : DiffUtil.ItemCallback<ItemModel>() {
        override fun areItemsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun createBinding(parent: ViewGroup): ListItemPeringkatBinding {
        val binding = DataBindingUtil.inflate<ListItemPeringkatBinding>(
            LayoutInflater.from(parent.context), R.layout.list_item_peringkat,
            parent, false
        )
        return binding
    }

    override fun bind(binding: ListItemPeringkatBinding, item: ItemModel) {
        binding.data = item

        val context: Context = binding.cvAva.context
        val id: Int = context.resources.getIdentifier(item.circle_ava, "drawable", context.packageName)
        val id2: Int = context.resources.getIdentifier(item.ava_icon, "drawable", context.packageName)
        binding.llAva.setBackgroundResource(id)
        binding.imgAva.setImageResource(id2)
    }
}
