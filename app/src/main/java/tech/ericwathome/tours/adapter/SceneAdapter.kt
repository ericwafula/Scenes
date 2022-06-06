package tech.ericwathome.tours.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import tech.ericwathome.tours.R
import tech.ericwathome.tours.data.DataManager
import tech.ericwathome.tours.model.SceneInfo
import tech.ericwathome.tours.util.TAG

class SceneAdapter(private val context: Context, private val scenes: ArrayList<SceneInfo>) :
    RecyclerView.Adapter<SceneAdapter.SceneViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SceneViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.scene_list_item, parent, false)
        return SceneViewHolder(view)
    }

    override fun onBindViewHolder(holder: SceneViewHolder, position: Int) {
        val scene = scenes[position]
        holder.setData(scene, position)
        holder.setListeners()
    }

    override fun getItemCount(): Int {
        return scenes.size
    }

    inner class SceneViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private var currentPosition = -1
        private lateinit var currentScene: SceneInfo

        private val sceneImage: ImageView = itemView.findViewById(R.id.scene_image)
        private val sceneTitle: TextView = itemView.findViewById(R.id.scene_title)
        private val heartIcon: ImageView = itemView.findViewById(R.id.heart_icon)
        private val trashIcon: ImageView = itemView.findViewById(R.id.trash_icon)

        private val heartOutlined = ResourcesCompat.getDrawable(context.resources, R.drawable.heart_outlined, null)
        private val heartFilled = ResourcesCompat.getDrawable(context.resources, R.drawable.heart_filled, null)
        private val trashOutlined = ResourcesCompat.getDrawable(context.resources, R.drawable.trash_outlined, null)

        fun setData(scene: SceneInfo, position: Int) {
            sceneImage.setImageResource(scene.imageId)
            sceneTitle.text = scene.title

            if (scene.isFavorite) {
                heartIcon.setImageDrawable(heartFilled)
            } else {
                heartIcon.setImageDrawable(heartOutlined)
            }

            trashIcon.setImageDrawable(trashOutlined)
            currentPosition = position
            currentScene = scene
        }

        fun setListeners() {
            heartIcon.setOnClickListener(this@SceneViewHolder)
            trashIcon.setOnClickListener(this@SceneViewHolder)
        }

        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.heart_icon -> addToFavorites()
                R.id.trash_icon -> deleteScene()
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
                heartIcon.setImageDrawable(heartFilled)
                DataManager.favoriteScenes.add(currentScene)
            } else {
                heartIcon.setImageDrawable(heartOutlined)
                DataManager.favoriteScenes.remove(currentScene)
            }
        }

    }
}