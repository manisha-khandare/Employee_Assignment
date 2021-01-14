package com.example.employeeapplication

import android.app.Application
import android.icu.util.LocaleData.getInstance
import android.os.AsyncTask
import android.webkit.WebViewDatabase.getInstance
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class EmployeeRepository(private val empDao: EmployeeDao) {


   // private var allEmployees: LiveData<List<Employee>>
    val allWords: Flow<List<Employee>> = empDao.getAlphabetizedWords()


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(employee: Employee) {
        empDao.insert(employee)
    }

}