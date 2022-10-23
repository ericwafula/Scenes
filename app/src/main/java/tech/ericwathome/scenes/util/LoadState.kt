package tech.ericwathome.scenes.util

class LoadState<T>(
    val data: T? = null,
    val errorMessage: String? = null,
    val loading: Boolean = false
)