package com.example.kotlinpractice.choiceLaunch.appdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kotlinpractice.choiceLaunch.dao.PersonDAO
import com.example.kotlinpractice.choiceLaunch.entity.PersonEntity

// Database 어노테이션 추가
// PersonEntity를 사용하며 version은 1, 스키마를 추출하지 않음으로 설정.
@Database(entities = [PersonEntity::class], version = 1, exportSchema = false)
abstract class ChoiceLaunchAppDatabase : RoomDatabase(){
    abstract fun personDao() : PersonDAO

    // 어디서든 접근 가능하고 중복 생성되지 않도록 싱글톤으로 companion object로 생성
    companion object{
        private var instance: ChoiceLaunchAppDatabase? =null

        @Synchronized
        fun getInstance(context: Context): ChoiceLaunchAppDatabase?{
            // DB이름은 database-choiceLaunch allowMainThreadQueries로 UI Thread(mainThread)에서 접근 가능하게 설정
            if(instance ==null){
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    ChoiceLaunchAppDatabase::class.java,
                    "database-choiceLaunch"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return instance
        }
    }
}