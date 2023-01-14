package tech.ericwathome.scenes.util

import androidx.recyclerview.widget.DiffUtil
import tech.ericwathome.scenes.feature_scenes.domain.model.Photo

object DiffCallback : DiffUtil.ItemCallback<Photo>() {
    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem.id == newItem.id
    }
}