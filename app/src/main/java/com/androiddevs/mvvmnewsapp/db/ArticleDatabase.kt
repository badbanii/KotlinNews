package com.androiddevs.mvvmnewsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.androiddevs.mvvmnewsapp.models.Article

@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class ArticleDatabase:RoomDatabase()
{
   abstract fun getArticleDao():ArticleDao

   ///Make sure that only one instance exists (also only one thread can modify it)
   companion object{
       @Volatile
       private var instance: ArticleDatabase?=null
       private val LOCK=Any()

       ///if database doesn't exists it will be created
       operator fun invoke(context: Context)= instance?: synchronized(LOCK)
       {
           instance?:createDatabase(context).also{instance=it}
       }

       private fun createDatabase(context:Context)=
           Room.databaseBuilder(
               context.applicationContext,
               ArticleDatabase::class.java,
               "articles.db"
           ).build()
   }
}