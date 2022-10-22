package tech.ericwathome.tours.util

import androidx.recyclerview.widget.DiffUtil
import tech.ericwathome.tours.domain.model.Photo

object DiffCallback : DiffUtil.ItemCallback<Photo>() {
    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem.id == newItem.id
    }
}