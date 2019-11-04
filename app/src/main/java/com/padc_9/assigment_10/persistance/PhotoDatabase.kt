package com.padc_9.assigment_10.persistance

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.padc_9.assigment_10.data.vos.PhotoVO
import com.padc_9.assigment_10.persistance.daos.PhotoDao
import com.padc_9.assigment_10.utils.DB_NAME

@Database(entities = [PhotoVO::class], exportSchema = false, version = 7)
abstract class PhotoDatabase: RoomDatabase() {

    abstract fun photoDao(): PhotoDao

    companion object {
        private var instance: PhotoDatabase? = null

        fun getInstance(context: Context): PhotoDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context, PhotoDatabase::class.java, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return instance!!
        }
    }
}