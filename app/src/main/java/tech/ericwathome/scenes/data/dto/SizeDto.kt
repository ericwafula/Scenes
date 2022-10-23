package tech.ericwathome.scenes.data.dto

import com.google.gson.annotations.SerializedName
import tech.ericwathome.scenes.domain.model.Size

data class SizeDto(
    val full: String,
    val raw: String,
    val regular: String,
    val small: String,
    @SerializedName("small_s3")
    val smallS3: String,
    val thumb: String
)

fun SizeDto.toSize(): Size {
    return Size(
        raw = raw,
        full = full,
        regular = regular,
        small = small,
        thumb = thumb
    )
}