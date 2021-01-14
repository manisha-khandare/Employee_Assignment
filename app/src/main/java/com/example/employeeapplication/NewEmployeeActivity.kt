package com.example.employeeapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels

class NewEmployeeActivity : AppCompatActivity() {

    private val employeeViewModel: EmployeeViewModel by viewModels {
        WordViewModelFactory((application as EmployeeApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_employee)

        val editnameView = findViewById<EditText>(R.id.edit_emp)
        val editsalView = findViewById<EditText>(R.id.edit_sal)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editnameView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val emp_name = editnameView.text.toString()
                val emp_sal = editsalView.text.toString()
                val employee =Employee(emp_name,emp_sal)
               /* replyIntent.putExtra(EXTRA_REPLY, employee.emp_name)
                replyIntent.putExtra(EXTRA_REPLY, employee.emp_sal)
                setResult(Activity.RESULT_OK, replyIntent)*/

                employeeViewModel.insert(employee)
            }
            finish()
        }
    }



}