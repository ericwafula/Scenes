package tech.ericwathome.tours.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import tech.ericwathome.tours.R
import tech.ericwathome.tours.data.DataManager
import tech.ericwathome.tours.databinding.SceneListItemBinding
import tech.ericwathome.tours.model.SceneInfo

class SceneAdapter(private val context: Context, private val scenes: ArrayList<SceneInfo>) :
    RecyclerView.Adapter<SceneAdapter.SceneViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SceneViewHolder {
        val binding = SceneListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return SceneViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SceneViewHolder, position: Int) {
        val scene = scenes[position]
        holder.setData(scene, position)
        holder.setListeners()
    }

    override fun getItemCount(): Int {
        return scenes.size
    }

    inner class SceneViewHolder(private val binding: SceneListItemBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {

        private var currentPosition = -1
        private lateinit var currentScene: SceneInfo

        private val heartOutlined = ResourcesCompat.getDrawable(context.resources, R.drawable.heart_outlined, null)
        private val heartFilled = ResourcesCompat.getDrawable(context.resources, R.drawable.heart_filled, null)
        private val trashOutlined = ResourcesCompat.getDrawable(context.resources, R.drawable.trash_outlined, null)

        fun setData(scene: SceneInfo, position: Int) {
            binding.sceneImage.setImageResource(scene.imageId)
            binding.description.text = scene.title

            if (scene.isFavorite) {
                binding.heartIcon.setImageDrawable(heartFilled)
            } else {
                binding.heartIcon.setImageDrawable(heartOutlined)
            }

            currentPosition = position
            currentScene = scene
        }

        fun setListeners() {
            binding.heartIcon.setOnClickListener(this@SceneViewHolder)
        }

        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.heart_icon -> addToFavorites()
            }
        }

        private fun deleteScene() {
            DataManager.scenes.remove(currentScene)
            DataManager.favoriteScenes.remove(currentScene)
            notifyItemRemoved(currentPosition)
            notifyItemRangeChanged(currentPosition, scenes.size)
        }

        private fun addToFavorites() {
            currentScene.isFavorite = !currentScene.isFavorite

            if (currentScene.isFavorite) {
                binding.heartIcon.setImageDrawable(heartFilled)
                DataManager.favoriteScenes.add(currentScene)
            } else {
                binding.heartIcon.setImageDrawable(heartOutlined)
                DataManager.favoriteScenes.remove(currentScene)
            }
        }

    }
}