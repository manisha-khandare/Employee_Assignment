package com.example.employeeapplication

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

interface EmployeeDao {
    @Insert
    fun insert(emp: Employee)

    @Query("DELETE FROM emp_table")
    fun deleteAllEmployees()

    @Query("SELECT emp_name FROM emp_table ")
    fun geEmployees(): LiveData<List<Employee>>

    @Query("SELECT emp_name FROM emp_table ORDER BY emp_name ASC")
    fun getAlphabetizedWords(): Flow<List<Employee>>


}