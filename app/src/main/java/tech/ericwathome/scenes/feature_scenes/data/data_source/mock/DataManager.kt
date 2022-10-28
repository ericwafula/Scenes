package tech.ericwathome.scenes.feature_scenes.data.data_source.mock


import tech.ericwathome.scenes.feature_scenes.domain.model.SceneInfo
import tech.ericwathome.tours.R

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