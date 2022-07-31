package tech.ericwathome.tours.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tech.ericwathome.tours.R
import tech.ericwathome.tours.databinding.FavoritesListItemBinding
import tech.ericwathome.tours.model.SceneInfo

class FavoritesAdapter(private val context: Context, private val favorites: ArrayList<SceneInfo>) : RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val binding = FavoritesListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return FavoritesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val scene = favorites[position]
        holder.setData(scene, position)
    }

    override fun getItemCount(): Int {
        return favorites.size
    }

    inner class FavoritesViewHolder(private val binding: FavoritesListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setData(scene: SceneInfo, position: Int) {
            binding.favoritesImage.setImageResource(scene.imageId)
            binding.favoritesTitle.text = scene.title
            binding.favoritesInfo.text = scene.info
        }

    }
}