package ilya.myasoedov.ocs.features.domain.entites

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Position (
    val id: String,
    val title: String,
    val location: String,
    val type: String,
    val description: String,
    val howToApply: String,
    val company: String,
    val companyUrl: String?,
    val companyLogo: String?,
    val url: String
) : Parcelable