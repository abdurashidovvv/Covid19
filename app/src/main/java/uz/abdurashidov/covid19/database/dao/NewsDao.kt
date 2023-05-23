package uz.abdurashidov.covid19.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import uz.abdurashidov.covid19.database.entity.Article

@Dao
interface NewsDao {

    @Insert
    suspend fun insertArticle(article: Article)

    @Query("select * from articles")
    suspend fun getAllArticles(): List<Article>

    @Delete
    suspend fun delete(article: Article)
}