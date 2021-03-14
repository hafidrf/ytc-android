package com.yureka.technology.ytc.ui.beranda.materi.practice2

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.yureka.technology.ytc.R
import com.yureka.technology.ytc.databinding.ListItemButtonPractice2Binding
import com.yureka.technology.ytc.ui.common.DataBoundListAdapter
import com.yureka.technology.ytc.ui.beranda.materi.practice2.model.ItemModel

class Practice2Adapter(
    private val itemClickCallback: ((ItemModel) -> Unit)?
) : DataBoundListAdapter<ItemModel, ListItemButtonPractice2Binding>(
    diffCallback = object : DiffUtil.ItemCallback<ItemModel>() {
        override fun areItemsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun createBinding(parent: ViewGroup): ListItemButtonPractice2Binding {
        val binding = DataBindingUtil.inflate<ListItemButtonPractice2Binding>(
            LayoutInflater.from(parent.context), R.layout.list_item_button_practice2,
            parent, false
        )

        binding.llButtonPractice2.setOnClickListener {
            binding.data?.let {
                itemClickCallback?.invoke(it)
            }
        }

        return binding
    }

    override fun bind(binding: ListItemButtonPractice2Binding, item: ItemModel) {
        binding.data = item

        val context: Context = binding.llButtonPractice2.context
        val id: Int = context.resources.getIdentifier(
            "rounded_rectangle_border_practice2",
            "drawable",
            context.packageName
        )
        val ids: Int = context.resources.getIdentifier(
            "rounded_rectangle_border_practice2_inactice",
            "drawable",
            context.packageName
        )

        if (item.isSelected) {
            //background ganti
            binding.llButtonPractice2.setBackgroundResource(id)
            binding.tvJawaban.setTextColor(Color.parseColor("#F05326"))
        } else {
            binding.llButtonPractice2.setBackgroundResource(ids)
            binding.tvJawaban.setTextColor(Color.parseColor("#8492A6"))
        }

    }

}