package com.yureka.technology.ytc.ui.beranda.materi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.yureka.technology.ytc.R
import com.yureka.technology.ytc.data.model.activity.Activity
import com.yureka.technology.ytc.databinding.ListItemMateriBinding
import com.yureka.technology.ytc.ui.common.DataBoundListAdapter

class MateriAdapter(
    private val itemClickCallback: ((Activity) -> Unit)?
) : DataBoundListAdapter<Activity, ListItemMateriBinding>(
    diffCallback = object : DiffUtil.ItemCallback<Activity>() {
        override fun areItemsTheSame(oldItem: Activity, newItem: Activity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Activity, newItem: Activity): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun createBinding(parent: ViewGroup): ListItemMateriBinding {
        val binding = DataBindingUtil.inflate<ListItemMateriBinding>(
            LayoutInflater.from(parent.context), R.layout.list_item_materi,
            parent, false
        )
        binding.clMateri.setOnClickListener {
            binding.data?.let {
                itemClickCallback?.invoke(it)
            }
        }

        return binding
    }

    override fun bind(binding: ListItemMateriBinding, item: Activity) {
        binding.data = item

        val context = binding.cvMateri.context

        val icon =
            context.resources.getIdentifier(item.image, "drawable", context.packageName)
        binding.imgAktivitasIcon.setImageResource(icon)

        // Temporary disable lock activity

        binding.imgAktivitasIcon.visibility = View.VISIBLE
        binding.imgBackground.visibility = View.INVISIBLE
        binding.imgLock.visibility = View.INVISIBLE

        /*if(item.activated){
//            binding.imgAktivitasIcon.visibility = View.VISIBLE
            binding.imgBackground.visibility = View.INVISIBLE
            binding.imgLock.visibility = View.INVISIBLE
        }else{
            binding.imgBackground.alpha = 0.3f
            binding.clMateri.isClickable = false
        }*/

    }
}