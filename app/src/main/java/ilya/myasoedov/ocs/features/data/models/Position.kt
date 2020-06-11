package ilya.myasoedov.ocs.features.data.models

import com.google.gson.annotations.SerializedName
import ilya.myasoedov.ocs.features.domain.entites.Position

data class Position(
    val id: String,
    @SerializedName("created_at") val createdAt: String,
    val title: String,
    val location: String,
    val type: String,
    val description: String,
    @SerializedName("how_to_apply") val howToApply: String,
    val company: String,
    @SerializedName("company_url") val companyUrl: String?,
    @SerializedName("company_logo") val companyLogo: String?,
    val url: String
) {

    fun toDomain(): Position {
        return Position(
            id = id,
            title = title,
            location = location,
            type = type,
            description = description,
            howToApply = howToApply,
            company = company,
            companyUrl = companyUrl,
            companyLogo = companyLogo,
            url = url
        )
    }
}