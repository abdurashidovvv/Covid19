package uz.abdurashidov.covid19.models.covidmodels

data class SummaryStats(
    val china: China,
    val global: Global,
    val nonChina: NonChina
)