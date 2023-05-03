package uz.abdurashidov.covid19.models.covidmodels

data class ResponseCovidData(
    val apiSourceCode: String,
    val cache: Cache,
    val dataSource: DataSource,
    val rawData: List<RawData>,
    val summaryStats: SummaryStats
)