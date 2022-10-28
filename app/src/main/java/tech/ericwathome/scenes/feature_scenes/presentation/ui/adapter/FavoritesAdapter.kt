package tech.ericwathome.scenes.feature_scenes.presentation.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tech.ericwathome.scenes.feature_scenes.domain.model.Photo
import tech.ericwathome.tours.databinding.FavoritesListItemBinding

class FavoritesAdapter(
    var favorites: List<Photo>,
    val handler: (Photo, Int) -> Unit
) :
    RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val binding = FavoritesListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoritesViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val scene = favorites[position]
        holder.bind(scene, position)
        holder.setListeners()
    }

    override fun getItemCount(): Int {
        return favorites.size
    }

    inner class FavoritesViewHolder(
        private val binding: FavoritesListItemBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        private lateinit var currentPhoto: Photo
        private var currentPosition: Int = -1
        private lateinit var currentFavorites: List<Photo>

        fun bind(photo: Photo, position: Int) {
            currentPhoto = photo
            currentPosition = position
            currentFavorites = favorites

            Glide.with(context)
                .load(photo.size?.regular)
                .into(binding.sceneImage)

            binding.username.text = photo.sponsorship?.sponsor?.username ?: "no username found"
            binding.likes.text = photo.likes.toString()
            binding.description.text = photo.description ?: "no description found"
        }

        fun setListeners() {
            binding.trashIcon.setOnClickListener(this@FavoritesViewHolder)
        }

        override fun onClick(v: View) {
            when (v.id) {
                binding.trashIcon.id -> deletePhoto(currentPhoto)
            }
        }

        private fun deletePhoto(currentPhoto: Photo) {
            handler(currentPhoto, currentPosition)
        }

    }
}