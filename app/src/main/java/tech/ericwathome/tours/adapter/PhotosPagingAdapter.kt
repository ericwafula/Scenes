package tech.ericwathome.tours.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tech.ericwathome.tours.databinding.SceneListItemBinding
import tech.ericwathome.tours.model.Photo
import tech.ericwathome.tours.util.DiffCallback

class PhotosPagingAdapter : PagingDataAdapter<Photo, PhotosPagingAdapter.PhotoViewHolder>(DiffCallback) {

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = SceneListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding, parent.context)
    }

    class PhotoViewHolder(private val binding: SceneListItemBinding, private val context: Context) : RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: Photo?) {
            Glide.with(context)
                .load(photo?.size?.regular)
                .into(binding.sceneImage)
            binding.likes.text = photo?.likes.toString()
            binding.username.text = photo?.sponsorship?.sponsor?.username
            binding.description.text = photo?.description
        }

    }

}