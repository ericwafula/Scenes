package tech.ericwathome.tours.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tech.ericwathome.tours.databinding.FavoritesListItemBinding
import tech.ericwathome.tours.model.Photo

class FavoritesAdapter(private val context: Context, var favorites: List<Photo>) :
    RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val binding = FavoritesListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return FavoritesViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val scene = favorites[position]
        holder.bind(scene, position)
    }

    override fun getItemCount(): Int {
        return favorites.size
    }

    inner class FavoritesViewHolder(
        private val binding: FavoritesListItemBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(photo: Photo, position: Int) {
            Glide.with(context)
                .load(photo.size?.regular)
                .into(binding.sceneImage)

            binding.username.text = photo.sponsorship?.sponsor?.username ?: "no username found"
            binding.likes.text = photo.likes.toString()
            binding.description.text = photo.description ?: "no description found"
        }

    }
}