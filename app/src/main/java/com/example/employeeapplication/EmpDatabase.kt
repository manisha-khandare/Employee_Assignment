package com.example.employeeapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Employee::class], version = 1)
abstract class EmpDatabase :RoomDatabase() {
    abstract  fun employeeDao(): EmployeeDao
   // abstract val roomCallback: RoomDatabase.Callback
   companion object {
       @Volatile
       private var INSTANCE: EmpDatabase? = null

       fun getDatabase(
           context: Context,
           scope: CoroutineScope
       ): EmpDatabase {
           // if the INSTANCE is not null, then return it,
           // if it is, then create the database
           return INSTANCE ?: synchronized(this) {
               val instance = Room.databaseBuilder(
                   context.applicationContext,
                   EmpDatabase::class.java,
                   "word_database"
               )
                   // Wipes and rebuilds instead of migrating if no Migration object.
                   // Migration is not part of this codelab.
                   .fallbackToDestructiveMigration()
                   .addCallback(WordDatabaseCallback(scope))
                   .build()
               INSTANCE = instance
               // return instance
               instance
           }
       }

       private class WordDatabaseCallback(
           private val scope: CoroutineScope
       ) : RoomDatabase.Callback() {
           /**
            * Override the onCreate method to populate the database.
            */
           override fun onCreate(db: SupportSQLiteDatabase) {
               super.onCreate(db)
               // If you want to keep the data through app restarts,
               // comment out the following line.
               INSTANCE?.let { database ->
                   scope.launch(Dispatchers.IO) {
                       populateDatabase(database.employeeDao())
                   }
               }
           }
       }

       /**
        * Populate the database in a new coroutine.
        * If you want to start with more words, just add them.
        */
       suspend fun populateDatabase(empDao: EmployeeDao) {
           // Start the app with a clean database every time.
           // Not needed if you only populate on creation.
           empDao.deleteAllEmployees()

           var employee = Employee("Manisha","10000")
           empDao.insert(employee)

       }
   }

}