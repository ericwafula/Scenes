package tech.ericwathome.scenes.data.dto

import com.google.gson.annotations.SerializedName
import tech.ericwathome.scenes.domain.model.Photo

data class PhotoDto(
    @SerializedName("alt_description")
    val altDescription: String?,
    @SerializedName("blur_hash")
    val blurHash: String,
    val color: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("current_user_collections")
    val currentUserCollections: List<Any>,
    val description: String?,
    val height: Int,
    val id: String,
    @SerializedName("liked_by_user")
    val likedByUser: Boolean,
    val likes: Int,
    @SerializedName("links")
    val linksDto: LinksDto,
    @SerializedName("promoted_at")
    val promotedAt: String,
    @SerializedName("sponsorship")
    val sponsorshipDto: SponsorshipDto,
    @SerializedName("topic_submissions")
    val topicSubmissions: TopicSubmissionsDto,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("urls")
    val sizeDto: SizeDto,
    val user: User,
    val width: Int
)

fun PhotoDto.toPhoto(): Photo {
    return Photo(
        id = id,
        description = description,
        altDescription = altDescription,
        size = sizeDto.toSize(),
        likes = likes,
        sponsorship = sponsorshipDto.toSponsorship()
    )
}