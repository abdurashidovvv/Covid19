package uz.abdurashidov.covid19.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
class Article(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    val url: String,
    val urlToImage: String
)