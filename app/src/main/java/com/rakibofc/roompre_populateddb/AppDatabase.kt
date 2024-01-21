package com.rakibofc.roompre_populateddb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [UserEntity::class, UserAddressEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase() : RoomDatabase() {

    abstract fun appDao(): AppDao

    companion object {

        private const val DATABASE_NAME = "app_database4.db"
        private const val PRE_DATABASE_NAME = "app_database"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .createFromAsset(DATABASE_NAME, object : PrepackagedDatabaseCallback() {

                        override fun onOpenPrepackagedDatabase(db: SupportSQLiteDatabase) {
                            super.onOpenPrepackagedDatabase(db)
                            deleteOldDatabase(context)
                        }
                    })
                    .build()
                INSTANCE = instance
                instance
            }
        }

        fun deleteOldDatabase(context: Context) {

            context.databaseList().forEach {

                if (DATABASE_NAME != it) {
                    if (it.contains(PRE_DATABASE_NAME))
                        context.deleteDatabase(it)
                }
            }
        }
    }
}