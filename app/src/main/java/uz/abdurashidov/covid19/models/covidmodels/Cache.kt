package uz.abdurashidov.covid19.models.covidmodels

data class Cache(
    val expires: String,
    val expiresTimestamp: Long,
    val lastUpdated: String,
    val lastUpdatedTimestamp: Long
)