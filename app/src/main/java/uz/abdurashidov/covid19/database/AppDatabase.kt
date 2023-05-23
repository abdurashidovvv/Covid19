package uz.abdurashidov.covid19.database

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.abdurashidov.covid19.database.dao.NewsDao
import uz.abdurashidov.covid19.database.entity.Article

@Database(entities = [Article::class], version = 3)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao
}
