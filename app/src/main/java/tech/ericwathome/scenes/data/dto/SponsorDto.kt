package tech.ericwathome.scenes.data.dto

import com.google.gson.annotations.SerializedName
import tech.ericwathome.scenes.domain.model.Sponsor

data class SponsorDto(
    @SerializedName("accepted_tos")
    val acceptedTos: Boolean,
    val bio: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("for_hire")
    val forHire: Boolean,
    val id: String,
    @SerializedName("instagram_username")
    val instagramUsername: String,
    @SerializedName("last_name")
    val last_name: String,
    val links: LinksXDto,
    val location: Any,
    val name: String,
    @SerializedName("portfolio_url")
    val portfolioUrl: String,
    @SerializedName("profile_image")
    val profileImage: ProfileImageDto,
    val socialDto: SocialDto,
    @SerializedName("total_collections")
    val totalCollections: Int,
    @SerializedName("total_likes")
    val totalLikes: Int,
    @SerializedName("total_photos")
    val totalPhotos: Int,
    @SerializedName("twitter_username")
    val twitterUsername: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    val username: String
)

fun SponsorDto.toSponsor(): Sponsor {
    return Sponsor(
        username = username
    )
}