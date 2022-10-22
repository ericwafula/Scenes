package tech.ericwathome.tours.data.mock

import tech.ericwathome.tours.R
import tech.ericwathome.tours.domain.model.SceneInfo

object DataManager {
    val scenes = ArrayList<SceneInfo>()
    val favoriteScenes = ArrayList<SceneInfo>()

    init {
        initializeScenes()
    }

    private fun initializeScenes() {
        var scene = SceneInfo(R.drawable.forest, "Nature", "Nature buddy", "Nature")
        scenes.add(scene)
        scene =
            SceneInfo(R.drawable.lens, "Through the lens", "Gotta love photography", "Photography")
        scenes.add(scene)
        scene = SceneInfo(R.drawable.woman, "Gender", "A voice for all genders", "Lifestyle")
        scenes.add(scene)
    }
}