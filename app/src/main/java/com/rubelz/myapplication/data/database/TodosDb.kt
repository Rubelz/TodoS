package com.rubelz.myapplication.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rubelz.myapplication.data.dao.TodoDao
import com.rubelz.myapplication.data.model.Todo

@Database(
    entities = [Todo::class],
    version = 1,
    exportSchema = false
)
abstract class TodosDb : RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        @Volatile
        var INSTANCE: TodosDb? = null
        fun getDbInstance(context: Context): TodosDb {
            val instance = INSTANCE
            if (instance != null) {
                return instance
            }
            synchronized(this) {
                val roomDbInstance =
                    Room.databaseBuilder(
                        context,
                        TodosDb::class.java,
                        "TodoDb"
                    ).build()
                INSTANCE = roomDbInstance

                return roomDbInstance

            }
        }
    }
}