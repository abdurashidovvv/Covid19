package uz.abdurashidov.covid19.repository

import uz.abdurashidov.covid19.database.dao.NewsDao
import uz.abdurashidov.covid19.database.entity.Article
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseRepository @Inject constructor(
    private val newsDao: NewsDao
){
    suspend fun getArticles() = newsDao.getAllArticles()
    suspend fun addArticle(article: Article) = newsDao.insertArticle(article)
    suspend fun deleteArticle(article: Article) = newsDao.delete(article)
}