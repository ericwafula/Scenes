package tech.ericwathome.tours.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tech.ericwathome.tours.R
import tech.ericwathome.tours.model.SceneInfo

class FavoritesAdapter(private val context: Context, private val favorites: ArrayList<SceneInfo>) : RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.favorites_list_item, parent, false)
        return FavoritesViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val scene = favorites[position]
        holder.setData(scene, position)
    }

    override fun getItemCount(): Int {
        return favorites.size
    }

    inner class FavoritesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val favoritesImage: ImageView = itemView.findViewById(R.id.favorites_image)
        private val favoritesTitle: TextView = itemView.findViewById(R.id.favorites_title)
        private val favoritesInfo: TextView = itemView.findViewById(R.id.favorites_info)

        fun setData(scene: SceneInfo, position: Int) {
            favoritesImage.setImageResource(scene.imageId)
            favoritesTitle.text = scene.title
            favoritesInfo.text = scene.info
        }

    }
}