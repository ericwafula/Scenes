package tech.ericwathome.scenes.data.dto

import tech.ericwathome.scenes.domain.model.Sponsorship

data class SponsorshipDto(
    val impression_urls: List<String>,
    val sponsorDto: SponsorDto,
    val tagline: String,
    val tagline_url: String
)

fun SponsorshipDto.toSponsorship(): Sponsorship {
    return Sponsorship(
        sponsor = sponsorDto.toSponsor()
    )
}