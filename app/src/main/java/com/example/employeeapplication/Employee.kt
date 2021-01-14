package com.example.employeeapplication

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "emp_table")
data class Employee(

    var emp_name: String,
    var emp_sal: String

) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}