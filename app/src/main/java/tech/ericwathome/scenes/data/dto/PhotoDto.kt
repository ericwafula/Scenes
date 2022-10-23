package tech.ericwathome.scenes.data.dto

data class PhotoDto(
    val alt_description: Any,
    val blur_hash: String,
    val color: String,
    val created_at: String,
    val current_user_collections: List<Any>,
    val description: Any,
    val height: Int,
    val id: String,
    val liked_by_user: Boolean,
    val likes: Int,
    val linksDto: LinksDto,
    val promoted_at: Any,
    val sponsorshipDto: SponsorshipDto,
    val topic_submissions: TopicSubmissionsDto,
    val updated_at: String,
    val urlsDto: UrlsDto,
    val user: User,
    val width: Int
)