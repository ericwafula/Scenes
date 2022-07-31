package tech.ericwathome.tours.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tech.ericwathome.tours.databinding.SceneListItemBinding
import tech.ericwathome.tours.model.Photo
import tech.ericwathome.tours.util.DiffCallback

class PhotosPagingAdapter(
    private val handler: (Photo) -> Unit
) : PagingDataAdapter<Photo, PhotosPagingAdapter.PhotoViewHolder>(DiffCallback) {

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.setListeners()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = SceneListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding, parent.context, handler)
    }

    class PhotoViewHolder(
        private val binding: SceneListItemBinding,
        private val context: Context,
        private val handler: (Photo) -> Unit
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        private lateinit var currentPhoto: Photo
        fun bind(photo: Photo?) {
            Glide.with(context)
                .load(photo?.size?.regular)
                .into(binding.sceneImage)
            binding.likes.text = photo?.likes.toString()
            binding.username.text = photo?.sponsorship?.sponsor?.username ?: "no username found"
            binding.description.text = photo?.description ?: "no description found"
            photo?.let {
                currentPhoto = it
            }
        }

        fun setListeners() {
            binding.bookmarkIcon.setOnClickListener(this@PhotoViewHolder)
        }

        override fun onClick(v: View?) {
            when (v?.id) {
                binding.bookmarkIcon.id -> addToBookmarks()
            }
        }

        private fun addToBookmarks() {
            handler(currentPhoto)
        }

    }

}