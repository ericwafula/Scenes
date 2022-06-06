package tech.ericwathome.tours.model

data class SceneInfo(var imageId: Int, var title: String, var info: String, var category: String, var isFavorite: Boolean = false) {
    override fun toString(): String {
        return title
    }
}