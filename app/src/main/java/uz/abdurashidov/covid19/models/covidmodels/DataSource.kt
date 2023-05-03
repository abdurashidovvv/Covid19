package uz.abdurashidov.covid19.models.covidmodels

data class DataSource(
    val lastGithubCommit: String,
    val publishedBy: String,
    val ref: String,
    val url: String
)