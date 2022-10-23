package tech.ericwathome.scenes.data.dto

data class SponsorshipDto(
    val impression_urls: List<String>,
    val sponsorDto: SponsorDto,
    val tagline: String,
    val tagline_url: String
)